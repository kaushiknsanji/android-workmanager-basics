# WorkManager Basics

**Blur-O-Matic** app built by following the instructions detailed in the Google Codelab **["Background Work with WorkManager"][WorkManager Basics Codelab]**. Original code by Google for this codelab can be referred [here][WorkManager Basics Repository].

## What one will learn

* Adding WorkManager to the Project
* Scheduling a `OneTimeWorkRequest`
* Configuring Input and Output parameters of a `Worker`
* Chaining WorkRequests together in order
* Naming a Work Chain with a Unique Name and configuring existing Work Policy
* Tagging WorkRequests and obtaining their `WorkInfo` to display its Work Status and read its Output `Data`
* Cancelling WorkRequests
* Configuring Constraints to a WorkRequest

## What is not taught

Advanced stuff like:
* Periodic Work requests
* Testing support library
* Parallel Work requests
* Input Mergers
* CoroutineWorker
* Threading in Workers

## Getting Started

* Android Studio 3.6 or higher with updated SDK and Gradle.
* Android device or emulator running API level 14+.

### Prerequisites
* Familiarity with the Kotlin programming language, object-oriented design concepts and Android Development Fundamentals
* Basic layouts, widgets and [View Bindings](https://d.android.com/topic/libraries/view-binding)
* Some familiarity with Uris and File I/O
* Familiarity with [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) and [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)

## Branches in this Repository

* **[starter-code-kotlin](https://github.com/kaushiknsanji/android-workmanager-basics/tree/starter-code-kotlin)**
	* This is the Starter code for the [codelab][WorkManager Basics Codelab].
* **[master](https://github.com/kaushiknsanji/android-workmanager-basics/tree/master)**
	* This contains the Solution for the [codelab][WorkManager Basics Codelab].
    * In comparison with the original [repository][WorkManager Basics Repository], this repository contains additional modifications and corrections-
		* Any previously finished work is cleared from WorkManager when it is first initialized to avoid showing the **"See File"** button when the `BlurActivity` is launched - [commit](https://github.com/kaushiknsanji/android-workmanager-basics/commits/master/bf6fe88d15a7bef56c12bf8e3ee80a32717c29ea).
		* Applying repeated blur for the chosen `blurLevel` is carried out in the [BlurWorker](https://github.com/kaushiknsanji/android-workmanager-basics/blob/b9fc18ea4c3982df57a7bd6250dc53ee348b27b5/app/src/main/java/com/example/background/workers/WorkerUtils.kt#L94-L171).
		* Logic for clearing temporary blurred image files is placed in the [BlurWorker](https://github.com/kaushiknsanji/android-workmanager-basics/blob/b9fc18ea4c3982df57a7bd6250dc53ee348b27b5/app/src/main/java/com/example/background/workers/WorkerUtils.kt#L202-L225).
		* Logic for saving the final blurred image to MediaStore filesystem is placed in the [BlurWorker](https://github.com/kaushiknsanji/android-workmanager-basics/blob/b9fc18ea4c3982df57a7bd6250dc53ee348b27b5/app/src/main/java/com/example/background/workers/WorkerUtils.kt#L227-L261).
		* Other minor changes to maintain idiomatic Kotlin usage.

## License

Copyright 2018 Google, Inc.

All image and audio files (including *.png, *.jpg, *.svg, *.mp3, *.wav
and *.ogg) are licensed under the CC BY 4.0 license. All other files are
licensed under the Apache 2 license.

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements.  See the LICENSE file distributed with this work for
additional information regarding copyright ownership.  The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License.  You may obtain a copy of
the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
License for the specific language governing permissions and limitations under
the License.		

<!-- Reference Style Links are to be placed after this -->
[WorkManager Basics Codelab]: https://codelabs.developers.google.com/codelabs/android-workmanager/index.html
[WorkManager Basics Repository]: https://github.com/googlecodelabs/android-workmanager/tree/kotlin
