package domanska.klaudia.kartylojalnosciowe.ui.cardList

import android.os.Bundle
import androidx.navigation.NavDirections
import domanska.klaudia.kartylojalnosciowe.R
import kotlin.Int
import kotlin.String

public class CardListFragmentDirections private constructor() {
  private data class ActionCardListFragmentToCardDetailsFragment(
    public val imagePath: String
  ) : NavDirections {
    public override fun getActionId(): Int = R.id.action_cardListFragment_to_cardDetailsFragment

    public override fun getArguments(): Bundle {
      val result = Bundle()
      result.putString("image_path", this.imagePath)
      return result
    }
  }

  public companion object {
    public fun actionCardListFragmentToCardDetailsFragment(imagePath: String): NavDirections =
        ActionCardListFragmentToCardDetailsFragment(imagePath)
  }
}
