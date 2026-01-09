package com.ougi.secira.authentication.domain.security

import com.auth0.jwk.JwkProvider
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.ougi.secira.authentication.domain.model.TokenPair
import io.ktor.util.decodeBase64Bytes
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.interfaces.ECPrivateKey
import java.security.interfaces.ECPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.util.Date
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.minutes


private const val USERNAME_CLAIM = "username"
internal const val TYPE_CLAIM = "type"

private const val ACCESS_TOKEN_AUDIENCE = "baseUserFlow"
private const val ACCESS_TOKEN_KID = "access"
private const val ACCESS_TOKEN_KEY_TYPE = "accessToken"
private const val REFRESH_TOKEN_AUDIENCE = "accessTokenRefresh"
private const val REFRESH_TOKEN_KID = "refresh"
private const val REFRESH_TOKEN_KEY_TYPE = "refreshToken"


internal class TokenFactory(
    private val issuer: String,
    private val accessJwkProvider: JwkProvider,
    private val refreshJwkProvider: JwkProvider,
    private val accessPrivateKeyText: String,
    private val refreshPrivateKeyText: String,
) {

    fun createTokenPair(
        username: String,
    ): TokenPair =
        TokenPair(
            accessToken = createAccessToken(username),
            refreshToken = createRefreshToken(username),
        )

    fun createAccessToken(
        username: String,
    ): String {
        val accessPublicKey = accessJwkProvider.get(ACCESS_TOKEN_KID).publicKey
        val accessPrivateKey = parsePrivateKey(accessPrivateKeyText)
        return createToken(
            audience = ACCESS_TOKEN_AUDIENCE,
            expiresMillis = 15.minutes.inWholeMilliseconds,
            keyType = ACCESS_TOKEN_KEY_TYPE,
            keyId = ACCESS_TOKEN_KID,
            issuer = issuer,
            username = username,
            publicKey = accessPublicKey,
            privateKey = accessPrivateKey,
        )
    }

    fun createRefreshToken(
        username: String,
    ): String {
        val refreshPublicKey = refreshJwkProvider.get(REFRESH_TOKEN_KID).publicKey
        val refreshPrivateKey = parsePrivateKey(refreshPrivateKeyText)
        return createToken(
            audience = REFRESH_TOKEN_AUDIENCE,
            expiresMillis = 2.days.inWholeMilliseconds,
            keyType = REFRESH_TOKEN_KEY_TYPE,
            keyId = REFRESH_TOKEN_KID,
            issuer = issuer,
            username = username,
            publicKey = refreshPublicKey,
            privateKey = refreshPrivateKey,
        )
    }

    private fun createToken(
        audience: String,
        issuer: String,
        username: String,
        keyType: String,
        keyId: String,
        expiresMillis: Long,
        publicKey: PublicKey,
        privateKey: PrivateKey,
    ): String =
        JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim(USERNAME_CLAIM, username)
            .withClaim(TYPE_CLAIM, keyType)
            .withKeyId(keyId)
            .withExpiresAt(Date(System.currentTimeMillis() + expiresMillis))
            .sign(Algorithm.ECDSA512(publicKey as ECPublicKey, privateKey as ECPrivateKey))

    private fun parsePrivateKey(key: String): PrivateKey =
        key.decodeBase64Bytes()
            .let(::PKCS8EncodedKeySpec)
            .let(KeyFactory.getInstance("EC")::generatePrivate)
}