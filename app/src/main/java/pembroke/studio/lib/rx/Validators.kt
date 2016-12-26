package pembroke.studio.lib.rx

import android.util.Patterns

fun isUsernameValid(username: String) = Patterns.EMAIL_ADDRESS.matcher(username).matches()
fun isPasswordValid(password: String) = password.length > 5