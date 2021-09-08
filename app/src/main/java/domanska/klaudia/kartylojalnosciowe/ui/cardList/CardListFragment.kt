package domanska.klaudia.kartylojalnosciowe.ui.cardList

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import domanska.klaudia.kartylojalnosciowe.databinding.FragmentCardListBinding
import domanska.klaudia.kartylojalnosciowe.ui.GridSpacingItemDecorator
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

const val REQUEST_IMAGE_CAPTURE = 1

class CardListFragment : Fragment() {
    private var _binding: FragmentCardListBinding? = null
    private val binding get() = _binding!!
    private var fileToCreate: File? = null
    private var adapter: CardListAdapter = CardListAdapter()
    private var cardNumber: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardListBinding.inflate(layoutInflater, container, false)

        binding.cardsRecyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.cardsRecyclerView.adapter = adapter
        binding.cardsRecyclerView.addItemDecoration(GridSpacingItemDecorator(8))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addCardFab.setOnClickListener {
            dispatchTakePictureIntent()
        }
    }

    override fun onResume() {
        super.onResume()

        val appSpecificExternalDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val imageList = appSpecificExternalDir?.listFiles()
        imageList?.let {
            it.reverse()
            adapter.setData(it)
            cardNumber = it.size
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == 0) {
            fileToCreate?.delete()
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val storageDir: File? = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File(storageDir, "Karta ${cardNumber + 1}.jpeg").apply { fileToCreate = this }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireContext().packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        requireContext(),
                        "com.example.android.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }
}