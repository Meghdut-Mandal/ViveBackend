package auth


import auth.JwtConfig.verifier
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.application.Application
import io.ktor.auth.authentication
import io.ktor.auth.jwt.JWTAuthenticationProvider
import io.ktor.auth.jwt.JWTPrincipal
import model.Teacher
import model.User
import java.util.*

data class Token (val token: String)

object JwtConfig {
    const val issuer = "viveduserv.in"
    private const val secret = "zAP5MBA4B4Ijz0MZaS48"
    private const val validityInMs = 3_600_000 * 24 * 7L // 1 week
    private val algorithm = Algorithm.HMAC512(secret)

    val verifier: JWTVerifier = JWT.require(algorithm).withIssuer(issuer).build()

    fun makeToken(user: Teacher): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withClaim("id", user.id)
        .withClaim("login", user.name)
        .withClaim("description", user.hash)
        .withArrayClaim("names", arrayOf(user.id, user.hash))
        .withExpiresAt(getExpiration())
        .sign(algorithm)

    private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)

}

