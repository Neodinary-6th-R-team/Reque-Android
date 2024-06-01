package com.neodinary.reque.mission.record

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.neodinary.reque.common.ui.base.BaseFragment
import com.neodinary.reque.mission.record.databinding.FragmentMissionRecordBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MissionRecordFragment : BaseFragment<FragmentMissionRecordBinding, MissionRecordViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_mission_record
    override val viewModel: MissionRecordViewModel by viewModels()

    // Uri.getPath로 File 생성해서 멀티파트로 전달
    private var captureImgUri : Uri? = null

    private val captureLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == -1){
            binding.captureImgUri = captureImgUri
        }
    }
    override fun initView() {

    }

    override fun initDataBinding() {
        binding.vm = viewModel
    }

    override fun initObserving() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.action.collect { action ->
                    when(action){
                        is MissionRecordAction.StartCapture -> {
                            checkCameraPermission()
                        }
                    }
                }
            }
        }
    }

    private fun checkCameraPermission() {
        TedPermission.create()
            .setPermissionListener(object : PermissionListener{
                override fun onPermissionGranted() {
                    startCaptureActivity()
                }

                override fun onPermissionDenied(p0: MutableList<String>?) {
                    Snackbar.make(binding.root, "카메라 권한이 거부되었습니다", Snackbar.LENGTH_SHORT).show()
                }
            }).setDeniedMessage("카메라 권한이 필요합니다.")
            .setPermissions(Manifest.permission.CAMERA).check()
    }

    private fun startCaptureActivity(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                withContext(Dispatchers.IO){
                    val photoFile = createImageFile()
                    captureImgUri = FileProvider.getUriForFile(requireContext(), "com.neodinary.reque.fileprovider", photoFile)
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, captureImgUri)
                    captureLauncher.launch(intent)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Snackbar.make(binding.root, "카메라 권한을 허용해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        )
    }
}