/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.background

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.background.databinding.ActivityBlurBinding

/**
 * Activity that inflates the layout 'R.layout.activity_blur' to allow the user
 * to apply the Blur filter on the Image shown.
 *
 * @author Kaushik N Sanji
 */
class BlurActivity : AppCompatActivity() {

    // Initialize the BlurViewModel
    private val viewModel by viewModels<BlurViewModel>()

    // ViewBinding instance for this activity
    private lateinit var binding: ActivityBlurBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate and bind the layout 'R.layout.activity_blur'
        binding = ActivityBlurBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Image URI should be stored in the ViewModel; put it there then display
        val imageUriExtra = intent.getStringExtra(KEY_IMAGE_URI)
        // Save the URI of the Image picked to the ViewModel
        viewModel.setImageUri(imageUriExtra)
        // Load the Image picked
        viewModel.imageUri?.let { imageUri ->
            Glide.with(this).load(imageUri).into(binding.imageView)
        }

        // Register a click listener on the "Go" button
        binding.goButton.setOnClickListener {
            // Delegate to the ViewModel to apply the Blur filter on the Image with the chosen Blur level
            viewModel.applyBlur(blurLevel)
        }
    }

    /**
     * Shows and hides views for when the Activity is processing an image
     */
    private fun showWorkInProgress() {
        with(binding) {
            progressBar.visibility = View.VISIBLE
            cancelButton.visibility = View.VISIBLE
            goButton.visibility = View.GONE
            seeFileButton.visibility = View.GONE
        }
    }

    /**
     * Shows and hides views for when the Activity is done processing an image
     */
    private fun showWorkFinished() {
        with(binding) {
            progressBar.visibility = View.GONE
            cancelButton.visibility = View.GONE
            goButton.visibility = View.VISIBLE
        }
    }

    // Retrieves the Blur level chosen by the user
    private val blurLevel: Int
        get() =
            when (binding.radioBlurGroup.checkedRadioButtonId) {
                R.id.radio_blur_lv_1 -> 1
                R.id.radio_blur_lv_2 -> 2
                R.id.radio_blur_lv_3 -> 3
                else -> 1
            }
}
