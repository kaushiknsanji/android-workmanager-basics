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

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.background.workers.BlurWorker

/**
 * [AndroidViewModel] subclass for [BlurActivity].
 *
 * @param application [Application] instance required for initializing a WorkManager.
 *
 * @author Kaushik N Sanji
 */
class BlurViewModel(application: Application) : AndroidViewModel(application) {

    // URI to the Source Image file
    internal var imageUri: Uri? = null

    // URI to the Output Blurred Image file
    internal var outputUri: Uri? = null

    // Instance of WorkManager
    private val workManager = WorkManager.getInstance(application)

    /**
     * Create the WorkRequest to apply the blur and save the resulting image
     *
     * @param blurLevel The amount to blur the image
     */
    internal fun applyBlur(blurLevel: Int) {
        // Create a one-off work request for applying the Blur filter on the Image
        // and schedule it with WorkManager
        workManager.enqueue(OneTimeWorkRequest.from(BlurWorker::class.java))
    }

    /**
     * Parses and converts [uriString] into [Uri] instance.
     *
     * @param uriString [String] instance containing the URI to a file.
     */
    private fun uriOrNull(uriString: String?): Uri? =
            uriString.isNullOrEmpty().takeIf { !it }?.let { Uri.parse(uriString) }

    /**
     * Sets the [imageUri] to the Source Image file given by the [uri] String.
     */
    internal fun setImageUri(uri: String?) {
        imageUri = uriOrNull(uri)
    }

    /**
     * Sets the [outputUri] to the Blurred Image file.
     */
    internal fun setOutputUri(outputImageUri: String?) {
        outputUri = uriOrNull(outputImageUri)
    }
}
