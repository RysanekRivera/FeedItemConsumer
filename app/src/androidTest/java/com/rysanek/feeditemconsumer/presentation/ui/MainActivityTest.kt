package com.rysanek.feeditemconsumer.presentation.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isPlatformPopup
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.rysanek.feeditemconsumer.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    
    @get: Rule
    val activityScenario = ActivityScenarioRule(MainActivity::class.java)
    
    @Test
    fun test_click_bigtop_success(){
        
        onView(withId(R.id.ivPopUpMenu)).check(matches(isDisplayed()))
    
        onView(withId(R.id.ivPopUpMenu)).perform(click())
    
        onView(withText("Big Top")).inRoot(isPlatformPopup()).perform(click())
        
    }
    
    @Test
    fun test_click_river_success(){
        
        onView(withId(R.id.ivPopUpMenu)).check(matches(isDisplayed()))
        
        onView(withId(R.id.ivPopUpMenu)).perform(click())
        
        onView(withText("River")).inRoot(isPlatformPopup()).perform(click())
        
    }
    
    @Test
    fun test_click_ad_success(){
        
        onView(withId(R.id.ivPopUpMenu)).check(matches(isDisplayed()))
        
        onView(withId(R.id.ivPopUpMenu)).perform(click())
        
        onView(withText("Ad")).inRoot(isPlatformPopup()).perform(click())
        
    }
    
    @Test
    fun test_click_house_ad_success(){
        
        onView(withId(R.id.ivPopUpMenu)).check(matches(isDisplayed()))
        
        onView(withId(R.id.ivPopUpMenu)).perform(click())
        
        onView(withText("House Ad")).inRoot(isPlatformPopup()).perform(click())
        
    }
    
    @Test
    fun test_click_house_slide_show_success(){
        
        onView(withId(R.id.ivPopUpMenu)).check(matches(isDisplayed()))
        
        onView(withId(R.id.ivPopUpMenu)).perform(click())
        
        onView(withText("Slide Show")).inRoot(isPlatformPopup()).perform(click())
        
    }
    
}