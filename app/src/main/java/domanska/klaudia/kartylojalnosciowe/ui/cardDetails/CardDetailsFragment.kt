package domanska.klaudia.kartylojalnosciowe.ui.cardDetails

import android.app.Activity
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import domanska.klaudia.kartylojalnosciowe.R
import domanska.klaudia.kartylojalnosciowe.databinding.FragmentCardDetailsBinding
import java.io.File

class CardDetailsFragment : Fragment() {
    private var _binding: FragmentCardDetailsBinding? = null
    private val binding get() = _binding!!
    private val navArgs by navArgs<CardDetailsFragmentArgs>()
    private lateinit var file: File

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardDetailsBinding.inflate(inflater, container, false)
        file = File(navArgs.imagePath)

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bitmap = BitmapFactory.decodeFile(file.absolutePath)

        binding.imageInputName.setText(file.nameWithoutExtension)
        binding.cardImageView.setImageBitmap(bitmap)

        binding.saveButton.setOnClickListener {
            val appSpecificExternalDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val imageList = appSpecificExternalDir?.listFiles()

            val newFileName = File(file.parent, binding.imageInputName.text.toString())

            if (imageList != null) {
                if (imageList.contains(newFileName)) {
                    Toast.makeText(requireContext(), getString(R.string.exists), Toast.LENGTH_LONG).show()
                } else {
                    file.renameTo(newFileName)
                    Toast.makeText(requireContext(), getString(R.string.saved), Toast.LENGTH_LONG).show()
                }
            } else {
                file.renameTo(newFileName)
                Toast.makeText(requireContext(), getString(R.string.saved), Toast.LENGTH_LONG).show()
            }

            val imm = requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken,0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.card_details_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.remove_card -> {
                showDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.remove))
            .setMessage(getString(R.string.sure))
            .setPositiveButton(R.string.yes) { _, _ ->
                file.delete()
                findNavController().navigateUp()
            }
            .setNegativeButton(R.string.no) { dialog, _ -> dialog.cancel() }
            .show()
    }
}