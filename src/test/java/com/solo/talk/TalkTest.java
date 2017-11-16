package com.solo.talk;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.solo.talk.Talk;
import com.solo.talk.TalkDurationReverseComparator;

/**
 * Class containing the tests for {@link Talk}
 * 
 * @author marumjr
 */
public class TalkTest {

	/**
	 * Integration test for all the methods of {@link Talk}
	 * <p>
	 * Creates some workshops using different constructors, and check if all
	 * attributes are correctly created
	 */
	@Test
	public void testWorkshop() {
		// Instantiating using one constructor...
		Talk w15 = new Talk("15 Minutes Workshop", 15);
		Assert.assertEquals("15 Minutes Workshop", w15.getName());
		Assert.assertEquals(15, w15.getDuration());
		Assert.assertEquals("15 Minutes Workshop (15 min)", w15.toString());

		// ... and now the other one
		Talk w30 = new Talk("30 Minutes Workshop", "30min");
		Assert.assertEquals("30 Minutes Workshop", w30.getName());
		Assert.assertEquals(30, w30.getDuration());
		Assert.assertEquals("30 Minutes Workshop (30 min)", w30.toString());

		// ... and the lightning kind
		Talk lightning = new Talk("Lightning Workshop", "lightning");
		Assert.assertEquals("Lightning Workshop", lightning.getName());
		Assert.assertEquals(5, lightning.getDuration());
		Assert.assertEquals("Lightning Workshop (5 min)", lightning.toString());
	}

	/**
	 * Integration tests for the {@link TalkDurationReverseComparator}
	 * <p>
	 * Check if the method
	 * {@link TalkDurationReverseComparator#compare(Talk, Talk)} is
	 * working properly and asserts that it can correctly sort a collection of
	 * Workshops
	 */
	@Test
	public void testWorkshopDurationReverseComparator() {
		Comparator<Talk> comparator = new TalkDurationReverseComparator();

		Talk w5 = new Talk("5 Minutes Workshop", 5);
		Talk lightning = new Talk("Lightning Workshop", "lightning");
		Talk w20 = new Talk("20 Minutes Workshop", "20min");
		Talk w35 = new Talk("35 Minutes Workshop", 35);

		Assert.assertEquals(0, comparator.compare(w5, lightning));
		Assert.assertEquals(1, comparator.compare(w5, w20));
		Assert.assertEquals(-1, comparator.compare(w35, w20));

		// Adding some elements in a random order
		List<Talk> workshops = new ArrayList<Talk>();
		workshops.add(new Talk("30 Minutes Workshop", "30min"));
		workshops.add(w5);
		workshops.add(new Talk("10 Minutes Workshop", 10));
		workshops.add(new Talk("45 Minutes Workshop", 45));
		workshops.add(new Talk("15 Minutes Workshop", 15));
		workshops.add(lightning);
		workshops.add(new Talk("50 Minutes Workshop", "50min"));
		workshops.add(new Talk("40 Minutes Workshop", "40min"));
		workshops.add(w35);
		workshops.add(w20);

		workshops.sort(comparator);

		Iterator<Talk> iterator = workshops.iterator();
		assertSameName(iterator, "50 Minutes Workshop");
		assertSameName(iterator, "45 Minutes Workshop");
		assertSameName(iterator, "40 Minutes Workshop");
		assertSameName(iterator, "35 Minutes Workshop");
		assertSameName(iterator, "30 Minutes Workshop");
		assertSameName(iterator, "20 Minutes Workshop");
		assertSameName(iterator, "15 Minutes Workshop");
		assertSameName(iterator, "10 Minutes Workshop");

		// These last elements could appear in any order, as they are equal in
		// weight to the comparator
		Talk nextWorkshop = iterator.next();
		Assert.assertTrue("5 Minutes Workshop".equals(nextWorkshop.getName())
				|| "Lightning Workshop".equals(nextWorkshop.getName()));
		nextWorkshop = iterator.next();
		Assert.assertTrue("5 Minutes Workshop".equals(nextWorkshop.getName())
				|| "Lightning Workshop".equals(nextWorkshop.getName()));

		// No more elements to check
		Assert.assertFalse(iterator.hasNext());
	}

	/**
	 * Assert that the iterator's next workshop has the expected name
	 * 
	 * @param iterator
	 *            Iterator containing the workshops
	 * @param expectedName
	 *            Expected name which to compare
	 */
	private void assertSameName(Iterator<Talk> iterator, String expectedName) {
		Talk nextWorkshop = iterator.next();
		Assert.assertEquals(expectedName, nextWorkshop.getName());
	}

}
