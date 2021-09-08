package domanska.klaudia.kartylojalnosciowe.ui.cardDetails

import android.os.Bundle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class CardDetailsFragmentArgs(
  public val imagePath: String
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("image_path", this.imagePath)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): CardDetailsFragmentArgs {
      bundle.setClassLoader(CardDetailsFragmentArgs::class.java.classLoader)
      val __imagePath : String?
      if (bundle.containsKey("image_path")) {
        __imagePath = bundle.getString("image_path")
        if (__imagePath == null) {
          throw IllegalArgumentException("Argument \"image_path\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"image_path\" is missing and does not have an android:defaultValue")
      }
      return CardDetailsFragmentArgs(__imagePath)
    }
  }
}
