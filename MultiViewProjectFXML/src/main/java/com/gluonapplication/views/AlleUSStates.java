/**
 * Copyright (c) 2016, Gluon
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *     * Neither the name of Gluon, any associated website, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL GLUON BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.gluonapplication.views;

import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.Cache;
import com.gluonhq.charm.down.plugins.CacheService;
import com.gluonhq.charm.down.plugins.StorageService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 *
 * List of US AlleUSStates Source: https://en.wikipedia.org/wiki/
 * List_of_states_and_territories_of_the_United_States Flag images available
 * under the Creative Commons CC0 1.0 Universal Public Domain Dedication
 *
 */
public class AlleUSStates {

	private static final Cache<String, Image> CACHE;

	static {
		CACHE = Services.get(CacheService.class).map(cache -> cache.<String, Image> getCache("images"))
				.orElseThrow(() -> new RuntimeException("No CacheService available"));
	}

	public static ObservableList<AlleUSState> statesList = FXCollections.observableArrayList(
			new AlleUSState("Alli Pierre Yotti", "35 ans", "Douala", 4833722, 135767, "/prof.png"),
			new AlleUSState("Alaska", "AK", "Yaounde", 735132, 1723337, "/avatar.png"),
			new AlleUSState("Arizona", "AZ", "Buea", 6626624, 295233, "/prof.png"),
			new AlleUSState("Arkansas", "AR", "Limbe", 2959373, 137733, "/avatar.png"),
			new AlleUSState("California", "CA", "Garoua", 38332521, 423968, "/s23.png"),
			new AlleUSState("Colorado", "CO", "Denver", 5268367, 269602, "/prof.png"),
			new AlleUSState("Connecticut", "CT", "Hartford", 3596080, 14356, "/avatar.png"),
			new AlleUSState("Delaware", "DE", "Dover", 925749, 6446, "/prof.png"),
			new AlleUSState("Florida", "FL", "Tallahassee", 19552860, 170312, "/s23.png"),
			new AlleUSState("Georgia", "GA", "Atlanta", 9992167, 153910, "/prof.png"),
			new AlleUSState("Hawaii", "HI", "Honolulu", 1404054, 28314, "/prof.png"),
			new AlleUSState("Idaho", "ID", "Boise", 1612136, 216443, "/prof.png"),
			new AlleUSState("Illinois", "IL", "Springfield", 12882135, 149997, "/prof.png"),
			new AlleUSState("Indiana", "IN", "Indianapolis", 6570902, 94327, "/s23.png"),
			new AlleUSState("Iowa", "IA", "Des Moines", 3090416, 145746, "/prof.png"),
			new AlleUSState("Kansas", "KS", "Topeka", 2893957, 213099, "/prof.png"),
			new AlleUSState("Kentucky", "KY", "Frankfort", 4395295, 104656, "/prof.png"),
			new AlleUSState("Louisiana", "LA", "Baton Rouge", 4625470, 135658, "/prof.png"),
			new AlleUSState("Maine", "ME", "Augusta", 1328302, 91634, "/prof.png"),
			new AlleUSState("Maryland", "MD", "Annapolis", 5928814, 32131, "/prof.png"),
			new AlleUSState("Massachusetts", "MA", "Boston", 6692824, 27335, "/prof.png"),
			new AlleUSState("Michigan", "MI", "Lansing", 9895622, 250488, "/prof.png"),
			new AlleUSState("Minnesota", "MN", "St. Paul", 5420380, 225163, "/prof.png"),
			new AlleUSState("Mississippi", "MS", "Jackson", 2991207, 125438, "/prof.png"),
			new AlleUSState("Missouri", "MO", "Jefferson City", 6021988, 180540, "/prof.png"),
			new AlleUSState("Montana", "MT", "Helena", 1015165, 380832, "/prof.png"),
			new AlleUSState("Nebraska", "NE", "Lincoln", 1868516, 200330, "/prof.png"),
			new AlleUSState("Nevada", "NV", "Carson City", 2790136, 286380, "/prof.png"),
			new AlleUSState("New Hampshire", "NH", "Concord", 1323459, 24214, "/prof.png"),
			new AlleUSState("New Jersey", "NJ", "Trenton", 8899339, 22592, "/prof.png"),
			new AlleUSState("New Mexico", "NM", "Santa Fe", 2085287, 314917, "/prof.png"),
			new AlleUSState("New York", "NY", "Albany", 19651127, 141297, "/prof.png"),
			new AlleUSState("North Carolina", "NC", "Raleigh", 9848060, 139391, "/prof.png"),
			new AlleUSState("North Dakota", "ND", "Bismarck", 723393, 183107, "/prof.png"),
			new AlleUSState("Ohio", "OH", "Columbus", 11570808, 116099, "/prof.png"),
			new AlleUSState("Oklahoma", "OK", "Oklahoma City", 3850568, 181038, "/prof.png"),
			new AlleUSState("Oregon", "OR", "Salem", 3930065, 254800, "/prof.png"),
			new AlleUSState("Pennsylvania", "PA", "Harrisburg", 12773801, 119279, "/prof.png"),
			new AlleUSState("Rhode Island", "RI", "Providence", 1051511, 4002, "/prof.png"),
			new AlleUSState("South Carolina", "SC", "Columbia", 4774839, 82931, "/prof.png"),
			new AlleUSState("South Dakota", "SD", "Pierre", 844877, 199730, "/prof.png"),
			new AlleUSState("Tennessee", "TN", "Nashville", 6495978, 109152, "/prof.png"),
			new AlleUSState("Texas", "TX", "Austin", 26448193, 695660, "/prof.png"),
			new AlleUSState("Utah", "UT", "Salt Lake City", 2900872, 219882, "/prof.png"),
			new AlleUSState("Vermont", "VT", "Montpelier", 626630, 24905, "/prof.png"),
			new AlleUSState("Virginia", "VA", "Richmond", 8260405, 110787, "/prof.png"),
			new AlleUSState("Washington", "WA", "Olympia", 6971406, 184661, "/prof.png"),
			new AlleUSState("West Virginia", "WV", "Charleston", 1854304, 62755, "/prof.png"),
			new AlleUSState("Wisconsin", "WI", "Madison", 5742713, 169634, "/prof.png"),
			new AlleUSState("Wyoming", "WY", "Cheyenne", 582658, 253335, "/prof.png"));

	public static Image getUSFlag() {
		return getImage("/prof.png");
		

	}

	/**
	 * This method will always return the required image. It will cache the
	 * image and return from cache if still there.
	 * 
	 * @param image:
	 *            A valid url to retrieve the image
	 * @return an Image
	 */
	public static Image getImage(String image) {
		if (image == null || image.isEmpty()) {
			return null;
		}
		Image cachedImage = CACHE.get(image);
		if (cachedImage == null) {
			cachedImage = new Image(image, true);
			CACHE.put(image, cachedImage);
		}
		return cachedImage;
	}
}
