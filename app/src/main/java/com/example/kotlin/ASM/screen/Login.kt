package com.example.kotlin.ASM.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.kotlin.ASM.dataClass.LoginRequest
import com.example.kotlin.ASM.viewModel.ViewModelLogin
import com.example.kotlin.R

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: ViewModelLogin = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    
    val context = LocalContext.current
    val loginResponse by loginViewModel.login.observeAsState()
    
    // Xử lý phản hồi từ API
    HandleLoginResponse(loginResponse, navController) { isLoadingNew ->
        isLoading = isLoadingNew
    }

    // Tạo các đối tượng state và events cho form
    val formState = LoginFormState(
        email = email,
        password = password,
        isPasswordVisible = isPasswordVisible,
        isLoading = isLoading
    )
    
    val formEvents = LoginFormEvents(
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onPasswordVisibilityToggle = { isPasswordVisible = !isPasswordVisible },
        onLoginClick = {
            handleLoginButtonClick(
                email = email,
                password = password,
                context = context,
                onLoading = { isLoading = it },
                loginViewModel = loginViewModel
            )
        },
        onSignUpClick = { navController.navigate("signup") }
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            LoginHeader()
            
            Spacer(modifier = Modifier.height(20.dp))
            
            // Form
            LoginForm(
                state = formState,
                events = formEvents
            )
        }
    }
}

@Composable
private fun HandleLoginResponse(
    loginResponse: Any?,
    navController: NavController,
    onLoadingChange: (Boolean) -> Unit
) {
    val context = LocalContext.current
    val viewModel = viewModel<ViewModelLogin>()
    
    LaunchedEffect(loginResponse) {
        if (loginResponse == null) return@LaunchedEffect
        
        onLoadingChange(false)
        
        if (loginResponse is com.example.kotlin.ASM.dataClass.LoginResponse) {
            if (loginResponse.success) {
                Toast.makeText(context, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show()
                // Reset trạng thái login trước khi chuyển màn hình
                viewModel.resetLoginState()
                navController.navigate("home", navOptions {
                    popUpTo("boarding") { inclusive = true }
                })
            } else {
                Toast.makeText(
                    context, 
                    "Đăng nhập thất bại. Vui lòng kiểm tra lại thông tin.", 
                    Toast.LENGTH_SHORT
                ).show()
                // Reset trạng thái login sau khi hiển thị thông báo lỗi
                viewModel.resetLoginState()
            }
        }
    }
}

private fun handleLoginButtonClick(
    email: String,
    password: String,
    context: android.content.Context,
    onLoading: (Boolean) -> Unit,
    loginViewModel: ViewModelLogin
) {
    if (email.isNotEmpty() && password.isNotEmpty()) {
        onLoading(true)
        loginViewModel.loginViewModel(LoginRequest(Email = email, Password = password))
    } else {
        Toast.makeText(
            context, 
            "Vui lòng nhập đầy đủ email và mật khẩu", 
            Toast.LENGTH_SHORT
        ).show()
    }
}

@Composable
private fun LoginHeader() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth(0.8f)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Icon with dividers
            LogoWithDividers()
            
            Spacer(modifier = Modifier.height(40.dp))
            
            // Welcome text
            WelcomeText()
        }
    }
}

@Composable
private fun LogoWithDividers() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        HorizontalDivider(
            modifier = Modifier
                .weight(1f)
                .padding(end = 10.dp),
            thickness = 1.dp,
            color = Color.LightGray
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_sofa),
            contentDescription = "App Icon",
            modifier = Modifier.size(64.dp)
        )
        HorizontalDivider(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp),
            thickness = 1.dp,
            color = Color.LightGray
        )
    }
}

@Composable
private fun WelcomeText() {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth(0.8f)
    ) {
        Text(
            text = "Hello !",
            fontSize = 30.sp,
            fontWeight = FontWeight.W400,
            color = Color(0xFF909090)
        )
        Text(
            text = "WELCOME BACK",
            fontSize = 24.sp,
            fontWeight = FontWeight.W700,
            color = Color.Black
        )
    }
}

// Data class để nhóm các tham số liên quan đến trạng thái form
data class LoginFormState(
    val email: String,
    val password: String,
    val isPasswordVisible: Boolean,
    val isLoading: Boolean
)

// Data class để nhóm các tham số liên quan đến sự kiện form
data class LoginFormEvents(
    val onEmailChange: (String) -> Unit,
    val onPasswordChange: (String) -> Unit,
    val onPasswordVisibilityToggle: () -> Unit,
    val onLoginClick: () -> Unit,
    val onSignUpClick: () -> Unit
)

@Composable
private fun LoginForm(
    state: LoginFormState,
    events: LoginFormEvents,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(0.8f),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Email field
            EmailField(state.email, events.onEmailChange)
            
            Spacer(modifier = Modifier.height(10.dp))
            
            // Password field
            PasswordField(
                password = state.password,
                isPasswordVisible = state.isPasswordVisible,
                onPasswordChange = events.onPasswordChange,
                onPasswordVisibilityToggle = events.onPasswordVisibilityToggle
            )
            
            Spacer(modifier = Modifier.height(40.dp))
            
            // Forgot password
            Text(
                text = "Forgot Password",
                color = Color.Black,
                fontSize = 16.sp
            )
            
            Spacer(modifier = Modifier.height(40.dp))
            
            // Login button
            LoginButton(state.isLoading, events.onLoginClick)
            
            Spacer(modifier = Modifier.height(40.dp))
            
            // Sign up text
            SignUpText(events.onSignUpClick)
        }
    }
}

@Composable
private fun EmailField(email: String, onEmailChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = onEmailChange,
        placeholder = { Text("Email") },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true
    )
}

@Composable
private fun PasswordField(
    password: String,
    isPasswordVisible: Boolean,
    onPasswordChange: (String) -> Unit,
    onPasswordVisibilityToggle: () -> Unit
) {
    TextField(
        value = password,
        onValueChange = onPasswordChange,
        placeholder = { Text("Password") },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        ),
        visualTransformation = if (isPasswordVisible) 
            VisualTransformation.None 
        else 
            PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        trailingIcon = {
            PasswordVisibilityToggle(
                isPasswordVisible = isPasswordVisible,
                onToggle = onPasswordVisibilityToggle
            )
        }
    )
}

@Composable
private fun PasswordVisibilityToggle(
    isPasswordVisible: Boolean,
    onToggle: () -> Unit
) {
    IconButton(onClick = onToggle) {
        Icon(
            painter = painterResource(
                id = if (isPasswordVisible) 
                    R.drawable.ic_visibility_off 
                else 
                    R.drawable.ic_visibility
            ),
            contentDescription = "Toggle Password Visibility"
        )
    }
}

@Composable
private fun LoginButton(isLoading: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .width(285.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(8.dp)),
        colors = ButtonDefaults.buttonColors(Color.Black),
        enabled = !isLoading
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = Color.White
            )
        } else {
            Text("Log in", color = Color.White)
        }
    }
}

@Composable
private fun SignUpText(onSignUpClick: () -> Unit) {
    Text(
        text = "Sign Up",
        color = Color.Black,
        fontSize = 16.sp,
        modifier = Modifier.clickable(onClick = onSignUpClick)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}