/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ke.co.calista.googlephotos.Utils

import android.app.Application
import com.google.api.gax.core.FixedCredentialsProvider
import com.google.auth.Credentials
import com.google.auth.oauth2.AccessToken
import com.google.auth.oauth2.UserCredentials
import com.google.photos.library.v1.PhotosLibraryClient
import com.google.photos.library.v1.PhotosLibrarySettings
import com.squareup.okhttp.*
import ke.co.calista.googlephotos.R

import java.io.IOException
import org.json.JSONException
import org.json.JSONObject


/**
 * A factory class that helps initialize a [PhotosLibraryClient] instance.
 */
object PhotosLibraryClientFactory {


    fun createClient(
            token: String): PhotosLibraryClient? {
        var settings: PhotosLibrarySettings? = null

        try {
            settings = PhotosLibrarySettings.newBuilder()
                    .setCredentialsProvider(
                            FixedCredentialsProvider.create(getUserCredentials(token)))
                    .build()
        } catch (e: IOException) {

            e.printStackTrace()
        }

        try {
            return PhotosLibraryClient.initialize(settings!!)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

    }

    private fun getUserCredentials(token: String): Credentials {


        val a = AccessToken(token, null)
        return UserCredentials.newBuilder()
                .setClientId("your client id")
                .setClientSecret("your client secret")
                .setAccessToken(a)
                .build()
    }

}
