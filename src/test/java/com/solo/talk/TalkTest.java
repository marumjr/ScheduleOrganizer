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
	 * Creates some talks using different constructors, and check if all
	 * attributes are correctly created
	 */
	@Test
	public void testTalk() {
		// Instantiating using one constructor...
		Talk w15 = new Talk("15 Minutes Talk", 15);
		Assert.assertEquals("15 Minutes Talk", w15.getName());
		Assert.assertEquals(15, w15.getDuration());
		Assert.assertEquals("15 Minutes Talk (15 min)", w15.toString());

		// ... and now the other one
		Talk w30 = new Talk("30 Minutes Talk", "30min");
		Assert.assertEquals("30 Minutes Talk", w30.getName());
		Assert.assertEquals(30, w30.getDuration());
		Assert.assertEquals("30 Minutes Talk (30 min)", w30.toString());

		// ... and the lightning kind
		Talk lightning = new Talk("Lightning Talk", "lightning");
		Assert.assertEquals("Lightning Talk", lightning.getName());
		Assert.assertEquals(5, lightning.getDuration());
		Assert.assertEquals("Lightning Talk (5 min)", lightning.toString());
	}

	/**
	 * Integration tests for the {@link TalkDurationReverseComparator}
	 * <p>
	 * Check if the method
	 * {@link TalkDurationReverseComparator#compare(Talk, Talk)} is
	 * working properly and asserts that it can correctly sort a collection of
	 * Talks
	 */
	@Test
	public void testTalkDurationReverseComparator() {
		Comparator<Talk> comparator = new TalkDurationReverseComparator();

		Talk w5 = new Talk("5 Minutes Talk", 5);
		Talk lightning = new Talk("Lightning Talk", "lightning");
		Talk w20 = new Talk("20 Minutes Talk", "20min");
		Talk w35 = new Talk("35 Minutes Talk", 35);

		Assert.assertEquals(0, comparator.compare(w5, lightning));
		Assert.assertEquals(1, comparator.compare(w5, w20));
		Assert.assertEquals(-1, comparator.compare(w35, w20));

		// Adding some elements in a random order
		List<Talk> talks = new ArrayList<Talk>();
		talks.add(new Talk("30 Minutes Talk", "30min"));
		talks.add(w5);
		talks.add(new Talk("10 Minutes Talk", 10));
		talks.add(new Talk("45 Minutes Talk", 45));
		talks.add(new Talk("15 Minutes Talk", 15));
		talks.add(lightning);
		talks.add(new Talk("50 Minutes Talk", "50min"));
		talks.add(new Talk("40 Minutes Talk", "40min"));
		talks.add(w35);
		talks.add(w20);

		talks.sort(comparator);

		Iterator<Talk> iterator = talks.iterator();
		assertSameName(iterator, "50 Minutes Talk");
		assertSameName(iterator, "45 Minutes Talk");
		assertSameName(iterator, "40 Minutes Talk");
		assertSameName(iterator, "35 Minutes Talk");
		assertSameName(iterator, "30 Minutes Talk");
		assertSameName(iterator, "20 Minutes Talk");
		assertSameName(iterator, "15 Minutes Talk");
		assertSameName(iterator, "10 Minutes Talk");

		// These last elements could appear in any order, as they are equal in
		// weight to the comparator
		Talk nextTalk = iterator.next();
		Assert.assertTrue("5 Minutes Talk".equals(nextTalk.getName())
				|| "Lightning Talk".equals(nextTalk.getName()));
		nextTalk = iterator.next();
		Assert.assertTrue("5 Minutes Talk".equals(nextTalk.getName())
				|| "Lightning Talk".equals(nextTalk.getName()));

		// No more elements to check
		Assert.assertFalse(iterator.hasNext());
	}

	/**
	 * Assert that the iterator's next talk has the expected name
	 * 
	 * @param iterator
	 *            Iterator containing the talks
	 * @param expectedName
	 *            Expected name which to compare
	 */
	private void assertSameName(Iterator<Talk> iterator, String expectedName) {
		Talk nextTalk = iterator.next();
		Assert.assertEquals(expectedName, nextTalk.getName());
	}

}
