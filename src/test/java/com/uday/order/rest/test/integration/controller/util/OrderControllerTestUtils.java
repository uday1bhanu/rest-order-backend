package com.uday.order.rest.test.integration.controller.util;

import static com.uday.order.rest.test.integration.controller.util.ControllerTestUtils.deleteLinkAtIndexIs;
import static com.uday.order.rest.test.integration.controller.util.ControllerTestUtils.deleteLinkIs;
import static com.uday.order.rest.test.integration.controller.util.ControllerTestUtils.selfLinkAtIndexIs;
import static com.uday.order.rest.test.integration.controller.util.ControllerTestUtils.selfLinkIs;
import static com.uday.order.rest.test.integration.controller.util.ControllerTestUtils.updateLinkAtIndexIs;
import static com.uday.order.rest.test.integration.controller.util.ControllerTestUtils.updateLinkIs;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.hateoas.EntityLinks;
import org.springframework.test.web.servlet.ResultMatcher;

import com.uday.order.rest.domain.Order;

public class OrderControllerTestUtils {

	public static ResultMatcher orderAtIndexIsCorrect(int index, Order expected) {
		return new CompositeResultMatcher()
			.addMatcher(jsonPath("$.[" + index + "].id").value(expected.getId()))
			.addMatcher(jsonPath("$.[" + index + "].description").value(expected.getDescription()))
			.addMatcher(jsonPath("$.[" + index + "].costInCents").value(expected.getCostInCents()))
			.addMatcher(jsonPath("$.[" + index + "].complete").value(expected.getIsComplete()));
	}
	
	public static ResultMatcher orderIsCorrect(Order expected) {
		return orderIsCorrect(expected.getId(), expected);
	}
	
	private static ResultMatcher orderIsCorrect(Long expectedId, Order expected) {
		return new CompositeResultMatcher().addMatcher(jsonPath("$.id").value(expectedId))
			.addMatcher(jsonPath("$.description").value(expected.getDescription()))
			.addMatcher(jsonPath("$.costInCents").value(expected.getCostInCents()))
			.addMatcher(jsonPath("$.complete").value(expected.getIsComplete()));
	}
	
	public static ResultMatcher updatedOrderIsCorrect(Long originalId, Order expected) {
		return orderIsCorrect(originalId, expected);
	}
	
	public static ResultMatcher orderLinksAtIndexAreCorrect(int index, Order expected, EntityLinks entityLinks) {
		final String selfReference = entityLinks.linkForSingleResource(expected).toString();
		
		return new CompositeResultMatcher()
			.addMatcher(selfLinkAtIndexIs(index, selfReference))
			.addMatcher(updateLinkAtIndexIs(index, selfReference))
			.addMatcher(deleteLinkAtIndexIs(index, selfReference));
	}
	
	public static ResultMatcher orderLinksAreCorrect(Order expected, EntityLinks entityLinks) {
		final String selfReference = entityLinks.linkForSingleResource(expected).toString();
		
		return new CompositeResultMatcher()
			.addMatcher(selfLinkIs(selfReference))
			.addMatcher(updateLinkIs(selfReference))
			.addMatcher(deleteLinkIs(selfReference));
	}
}
