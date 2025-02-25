package com.example.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.example.app.domain.RecordDB;
import com.example.app.mapper.RecordDBMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecordDBServiceImpl implements RecordDBService {

	private final RecordDBMapper mp;

	@Override
	public Integer[] getCountsForTodayAndYesterday() {

		List<RecordDB> lists = mp.selectAll();

		// 今日と昨日の日付を事前に取得
		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);

		// 相談件数をカウントする変数　forEach文のためスレッドセーフにする
		AtomicInteger todayCount = new AtomicInteger(0);
		AtomicInteger yesterdayCount = new AtomicInteger(0);

		// forEachメソッドを使用してカウント
		lists.forEach(list -> {
			LocalDate startAt = list.getStartAt();
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
			list.add(mp.countLastAndThisWeek(i));
		}
		return list;
	}

	@Override
	public List<Integer> getCountsForLastWeek() {
		List<Integer> list = new ArrayList<>();

		for (int i = -6; i <= 0; ++i) {
			list.add(mp.countLastAndThisWeek(i));
		}
		return list;
	}

}
