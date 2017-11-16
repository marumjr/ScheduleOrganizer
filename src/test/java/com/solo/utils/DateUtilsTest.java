package com.solo.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

/**
 * Class containing the tests for {@link DateUtils}
 * 
 * @author marumjr
 */
public class DateUtilsTest {

	/**
	 * Checks if {@link DateUtils#createDate(int, int)} creates a date properly
	 */
	public void testCreateDate() {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR_OF_DAY, 12);
		cal.set(Calendar.MINUTE, 30);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date expectedDate = cal.getTime();

		Date createdDate = DateUtils.createDate(12, 30);

		Assert.assertEquals(expectedDate, createdDate);
	}

	/**
	 * Checks if {@link DateUtils#addMinutesToDate(Date, int)} is working.
	 * <p>
	 * It tries to add some different values to a default date and assert it's
	 * the expected result.
	 */
	@Test
	public void testAddMinutesToDate() {
		Date midday = DateUtils.createDate(12, 0);

		// Checking if the sum is correct
		Assert.assertEquals(DateUtils.createDate(12, 30), DateUtils.addMinutesToDate(midday, 30));
		Assert.assertEquals(DateUtils.createDate(13, 0), DateUtils.addMinutesToDate(midday, 60));
		Assert.assertEquals(DateUtils.createDate(13, 30), DateUtils.addMinutesToDate(midday, 90));
		Assert.assertEquals(DateUtils.createDate(14, 0), DateUtils.addMinutesToDate(midday, 120));

		// It's possible to add negative values too, so...
		Assert.assertEquals(DateUtils.createDate(10, 0), DateUtils.addMinutesToDate(midday, -120));
		Assert.assertEquals(DateUtils.createDate(10, 30), DateUtils.addMinutesToDate(midday, -90));
		Assert.assertEquals(DateUtils.createDate(11, 0), DateUtils.addMinutesToDate(midday, -60));
		Assert.assertEquals(DateUtils.createDate(11, 30), DateUtils.addMinutesToDate(midday, -30));
	}

	/**
	 * Checks if {@link DateUtils#isDateInPeriod(Date, Date, Date)} is working.
	 * <p>
	 * Tests different dates against a fixed period, checking the following cases:
	 * <ul>
	 * <li>Date within the period;</li>
	 * <li>Dates out of the period;</li>
	 * <li>Dates barely within the period.</li>
	 * </ul>
	 */
	@Test
	public void testIsDateInPeriod() {
		Date midday = DateUtils.createDate(12, 0);
		Date oneOClock = DateUtils.createDate(13, 0);

		// Testing first a date that's in the middle
		Date date = DateUtils.createDate(12, 30);
		Assert.assertEquals(true, DateUtils.isDateInPeriod(date, midday, oneOClock));

		// Now one that barely miss
		date = DateUtils.createDate(11, 59);
		Assert.assertEquals(false, DateUtils.isDateInPeriod(date, midday, oneOClock));

		// One that is on the mark
		date = DateUtils.createDate(12, 0);
		Assert.assertEquals(true, DateUtils.isDateInPeriod(date, midday, oneOClock));

		// Another one on the mark, but in the opposite side this time
		date = DateUtils.createDate(13, 0);
		Assert.assertEquals(true, DateUtils.isDateInPeriod(date, midday, oneOClock));

		// And another one that narrowly miss, on the other extremity
		date = DateUtils.createDate(13, 1);
		Assert.assertEquals(false, DateUtils.isDateInPeriod(date, midday, oneOClock));
	}

}
