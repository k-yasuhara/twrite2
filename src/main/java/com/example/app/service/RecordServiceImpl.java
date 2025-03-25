package com.example.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.example.app.domain.Patient;
import com.example.app.domain.RecordDB;
import com.example.app.domain.Staff;
import com.example.app.domain.SymptomsPattern;
import com.example.app.dto.SymptomsCount;
import com.example.app.mapper.PatientMapper;
import com.example.app.mapper.RecordMapper;
import com.example.app.mapper.StaffMapper;
import com.example.app.mapper.SymptomPatternMapper;
import com.example.app.mapper.SymptomsMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

	private final RecordMapper recordMapper;
	private final StaffMapper staffMapper;
	private final PatientMapper patientMapper;
	private final SymptomPatternMapper symptomPatternMapper;
	private final SymptomsMapper symptomsMapper;

	@Override
	public Integer[] getCountsForTodayAndYesterday() {

		List<RecordDB> lists = recordMapper.selectAll();

		// 今日と昨日の日付を事前に取得
		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);

		// 相談件数をカウントする変数　forEach文のためスレッドセーフにする
		AtomicInteger todayCount = new AtomicInteger(0);
		AtomicInteger yesterdayCount = new AtomicInteger(0);

		// forEachメソッドを使用してカウント
		lists.forEach(list -> {
			LocalDate startAt = list.getStartAt().toLocalDate();
			if (startAt.isEqual(today)) {
				todayCount.incrementAndGet();
			} else if (startAt.isEqual(yesterday)) {
				yesterdayCount.incrementAndGet();
			}
		});

		//配列に格納して戻り値に渡す
		return new Integer[] { todayCount.get(), yesterdayCount.get() };
	}

	@Override
	public List<Integer> getCountsForThisWeek() {
		List<Integer> list = new ArrayList<>();

		for (int i = 1; i <= 7; ++i) {
			list.add(recordMapper.countLastAndThisWeek(i));
		}
		return list;
	}

	@Override
	public List<Integer> getCountsForLastWeek() {
		List<Integer> list = new ArrayList<>();

		for (int i = -6; i <= 0; ++i) {
			list.add(recordMapper.countLastAndThisWeek(i));
		}
		return list;
	}

	@Override
	public List<SymptomsCount> getTopSymptomsToday() {
		List<SymptomsCount> todayList = recordMapper.getTop3TodaySymptoms();
		if (todayList.isEmpty()) {
			return null;
		}
		List<SymptomsCount> yesterdayList = recordMapper.getTop3YesterdaySymptoms();

		for (SymptomsCount yl : yesterdayList) {
			updateDiffYesterday(todayList, yl);
		}

		for (SymptomsCount tl : todayList) {
			if (tl.getDiffYesterdayString() == null) {
				tl.setDiffYesterdayString("+" + tl.getCount());
			}
		}
		return todayList;
	}

	private void updateDiffYesterday(List<SymptomsCount> todayList, SymptomsCount yl) {
		todayList.stream()
				.filter(todaySymptom -> todaySymptom.getSymptomsName().equals(yl.getSymptomsName()))
				.findFirst()
				.ifPresent(todaySymptom -> {
					int todayCount = Optional.ofNullable(todaySymptom.getCount()).orElse(0);
					int yesterdayCount = Optional.ofNullable(yl.getCount()).orElse(0);
					int diff = todayCount - yesterdayCount;

					String diffString;
					if (diff > 0) {
						diffString = "+" + diff;
					} else if (diff < 0) {
						diffString = String.valueOf(diff);
					} else {
						diffString = "±0";
					}
					todaySymptom.setDiffYesterdayString(diffString);
				});
	}

	@Override
	public List<Staff> getStaffType() {
		return staffMapper.selectAll();
	}

	@Override
	public List<Patient> getPatientType() {
		return patientMapper.selectAll();
	}

	@Override
	public List<SymptomsPattern> getSymptomType() {
		return symptomPatternMapper.selectAll();
	}

	@Override
	public Integer addRecord(RecordDB r) {
		recordMapper.insert(r);
		return recordMapper.getLastInsertId();
	}

	@Override
	public void addSymptoms(int recordsId, int symptomsId) {
		symptomsMapper.insert(recordsId, symptomsId);
	}

	@Override
	public List<RecordDB> getRecordsWithDetails() {
		return recordMapper.selectAllRecordsWithDetails();
	}

	@Override
	public List<RecordDB> getUnapprovedRecords() {
		return recordMapper.findUnapprovedRecords();
	}

}
