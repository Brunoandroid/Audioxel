package com.example.audioxel.data.security

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.content.edit
import kotlin.getValue

@Singleton
class SecureTokenStore @Inject constructor(
    @param:ApplicationContext private val context: Context
) : ISecureTokenStore {

    private val masterKey: MasterKey by lazy {
        MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    private val prefs by lazy {
        runCatching { createSharedPrefs() }.getOrElse {
            deleteSharedPreferences()
            createSharedPrefs()
        }
    }

    private fun deleteSharedPreferences() {
        runCatching {
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit { clear() }
            context.deleteSharedPreferences(PREFS_NAME)
        }
    }

    private fun createSharedPrefs() = EncryptedSharedPreferences.create(
        context,
        PREFS_NAME,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override fun saveAccessToken(token: String) {
        runCatching { prefs.edit { putString(KEY_ACCESS_TOKEN, token) } }
    }

    override fun getAccessToken(): String? {
        return runCatching { prefs.getString(KEY_ACCESS_TOKEN, null) }.getOrNull()
    }

    override fun clearAccessToken() {
        runCatching { prefs.edit { remove(KEY_ACCESS_TOKEN) } }
    }

    private companion object {
        private const val PREFS_NAME = "audioxel_secure_prefs"
        private const val KEY_ACCESS_TOKEN = "access_token"
    }
}
