package com.example.kotlin.ASM.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kotlin.ASM.dataClass.Account
import com.example.kotlin.ASM.viewModel.ViewModelSignup
import com.example.kotlin.R

// Helper function to validate signup inputs
private fun validateSignupInputs(
    name: String,
    email: String,
    password: String,
    confirmPassword: String,
    context: android.content.Context
): Boolean {
    val isValid = when {
        name.isBlank() -> {
            Toast.makeText(context, "Vui lòng nhập tên của bạn", Toast.LENGTH_SHORT).show()
            false
        }

        email.isBlank() -> {
            Toast.makeText(context, "Vui lòng nhập email của bạn", Toast.LENGTH_SHORT).show()
            false
        }

        !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
            Toast.makeText(context, "Vui lòng nhập email hợp lệ", Toast.LENGTH_SHORT).show()
            false
        }

        password.isBlank() -> {
            Toast.makeText(context, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show()
            false
        }

        password.length < 6 -> {
            Toast.makeText(context, "Mật khẩu phải có ít nhất 6 ký tự", Toast.LENGTH_SHORT).show()
            false
        }

        password != confirmPassword -> {
            Toast.makeText(context, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show()
            false
        }

        else -> true
    }
    return isValid
}

// Extracted UI components
@Composable
private fun LogoHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 30.dp)
    ) {
        HorizontalDivider(
            modifier = Modifier
                .weight(1f)
                .height(1.dp),
            color = Color.Gray
        )
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_sofa),
            contentDescription = "Logo",
            modifier = Modifier
                .size(50.dp)
                .background(Color.White, shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        HorizontalDivider(
            modifier = Modifier
                .weight(1f)
                .height(1.dp),
            color = Color.Gray
        )
    }
}

@Composable
private fun WelcomeText() {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "WELCOME",
            fontSize = 24.sp,
            color = Color.Black
        )
    }
}

@Composable
private fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    passwordVisible: Boolean,
    onTogglePasswordVisibility: () -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        ),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image =
                if (passwordVisible) painterResource(id = R.drawable.ic_visibility) else painterResource(
                    id = R.drawable.ic_visibility_off
                )
            IconButton(onClick = onTogglePasswordVisibility) {
                Icon(painter = image, contentDescription = null)
            }
        },
        singleLine = true
    )
}

@Composable
private fun SignupButton(
    isLoading: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
        enabled = !isLoading
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = Color.White
            )
        } else {
            Text(text = "SIGN UP", color = Color.White)
        }
    }
}

@Composable
private fun LoginPrompt(onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 4.dp)
    ) {
        Text(
            text = "Already have an account?",
            color = Color.Gray
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "Log In",
            color = Color.Black,
            fontSize = 16.sp,
            modifier = Modifier.clickable(onClick = onClick),
        )
    }
}

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModelSignup: ViewModelSignup = viewModel()
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    val context = LocalContext.current
    val signupResponse = viewModelSignup.signup.observeAsState()

    // Observe signup response
    LaunchedEffect(signupResponse.value) {
        signupResponse.value?.let { response ->
            isLoading = false
            if (response.success) {
                // Signup successful
                Toast.makeText(context, "Đăng ký thành công!", Toast.LENGTH_SHORT).show()
                viewModelSignup.resetSignupState()
                navController.navigate("login")
            } else {
                // Signup failed
                errorMessage = "Đăng ký thất bại. Vui lòng thử lại."
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                viewModelSignup.resetSignupState()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE5E5E5)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
                .fillMaxSize()
                .wrapContentWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoHeader()

            Spacer(modifier = Modifier.height(60.dp))

            WelcomeText()

            Spacer(modifier = Modifier.height(60.dp))

            // Name field
            TextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text("Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Black,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Email field
            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Black,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Password field
            PasswordField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Password",
                passwordVisible = passwordVisible,
                onTogglePasswordVisibility = { passwordVisible = !passwordVisible }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Confirm password field
            PasswordField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = "Confirm Password",
                passwordVisible = confirmPasswordVisible,
                onTogglePasswordVisibility = { confirmPasswordVisible = !confirmPasswordVisible }
            )

            Spacer(modifier = Modifier.height(50.dp))

            // Signup button
            SignupButton(
                isLoading = isLoading,
                onClick = {
                    if (validateSignupInputs(name, email, password, confirmPassword, context)) {
                        isLoading = true
                        val account = Account(
                            Email = email,
                            FullName = name,
                            Password = password
                        )
                        viewModelSignup.signupViewModel(account)
                    }
                }
            )

            Spacer(modifier = Modifier.height(50.dp))

            // Login prompt
            LoginPrompt(onClick = { navController.navigate("login") })
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignUpScreenPreview() {
    val navController = rememberNavController()
    SignUpScreen(navController = navController)
}