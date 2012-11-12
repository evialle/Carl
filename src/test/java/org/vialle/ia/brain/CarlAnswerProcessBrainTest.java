/**
 * 
 */
package org.vialle.ia.brain;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Eric
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class CarlAnswerProcessBrainTest {
	
	@Inject
	CarlAnswerProcessBrain carlAnswerProcessBrain;

	/**
	 * Test method for {@link org.vialle.ia.brain.CarlAnswerProcessBrain#processAnswer(org.vialle.ia.brain.CarlAiAnswer)}.
	 * @throws Exception 
	 */
	@Test
	public void testProcessAnswer() throws Exception {
		
		CarlAiAnswer answer = new CarlAiAnswer();
		answer.setAction("lastNews");
		answer.setSpeak("");
		carlAnswerProcessBrain.processAnswer(answer);
	}

}
