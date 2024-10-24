package hu.bme.aut.highlowgame

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import hu.bme.aut.highlowgame.ui.theme.HighLowGameTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun startGameTest() {
        // Given
        // Start the app
        composeTestRule.setContent {
            HighLowGameTheme {
                MainNavHost()
            }
        }


        // When
        composeTestRule.onNodeWithText("Start").performClick()

        // Then
        //composeTestRule.onNodeWithText(text = "Guess", substring = true).assertIsDisplayed()

        composeTestRule.onNodeWithTag("guessbtn").assertIsDisplayed()
        //composeTestRule.onNodeWithText(text = "Restart").assertIsDisplayed()
    }

    @Test
    fun showAboutTest() {
        // Given
        // Start the app
        composeTestRule.setContent {
            HighLowGameTheme {
                MainNavHost()
            }
        }

        // When
        composeTestRule.onNodeWithText("About").performClick()

        // Then
        composeTestRule.onNodeWithText(text = "Demo app for Jetpack Compose").assertIsDisplayed()
    }

    @Test
    fun restartTest() {
        // Given
        // Start the app
        composeTestRule.setContent {
            HighLowGameTheme {
                MainNavHost()
            }
        }

        // When
        composeTestRule.onNodeWithText("Start").performClick()

        // Then
        composeTestRule.onNodeWithText(text = "Guess (0)").assertIsDisplayed()
        composeTestRule.onNodeWithText(text = "Guess", substring = true).performClick()
        composeTestRule.onNodeWithText(text = "Guess", substring = true).performClick()
        composeTestRule.onNodeWithText(text = "Guess", substring = true).performClick()

        composeTestRule.onNodeWithText(text = "Restart", substring = true).performClick()
        composeTestRule.onNodeWithText(text = "Guess (0)").assertIsDisplayed()
    }
}