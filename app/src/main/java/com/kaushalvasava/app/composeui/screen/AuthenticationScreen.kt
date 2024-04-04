package com.kaushalvasava.app.composeui.screen

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kaushalvasava.app.composeui.R
import com.kaushalvasava.app.composeui.ui.navigation.NavigationItem
import com.kaushalvasava.app.composeui.util.ValidUtil.isValidEmail
import com.kaushalvasava.app.composeui.util.ValidUtil.isValidName
import com.kaushalvasava.app.composeui.util.ValidUtil.isValidPasswordFormat

@Preview(showBackground = false)
@Composable
fun AuthenticationScreen(navController: NavController = rememberNavController()) {

    var isNewUser by rememberSaveable {
        mutableStateOf(true)
    }
    val backgroundColor = MaterialTheme.colorScheme.background
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .drawBehind {
                drawRect(
                    Brush.linearGradient(
                        colors = listOf(
                            Color.Blue,
                            backgroundColor,
                            Color.Blue,
                        )
                    )
                )
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                if (isNewUser) {
                    "Don't have an account?"
                } else {
                    "Already have an account?"
                },
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    isNewUser = !isNewUser
                }, colors = ButtonDefaults.buttonColors(
                    containerColor = backgroundColor.copy(0.3f),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                AnimatedContent(targetState = isNewUser, label = "") {
                    Text(
                        if (it) {
                            "Sign up"
                        } else {
                            "Sign in"
                        }
                    )
                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.s_2),
            contentScale = ContentScale.Crop,
            contentDescription = "logo",
            modifier = Modifier
                .size(200.dp, 150.dp)
                .rotate(-20f)
                .clip(RoundedCornerShape(16.dp))
                .align(Alignment.CenterHorizontally)

        )
        Text(
            "Unique",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
            thickness = 12.dp, color = MaterialTheme.colorScheme.background.copy(0.5f)
        )
        InfoCard(modifier = Modifier, isNewUser = { isNewUser }) {
            navController.navigate(NavigationItem.PRODUCTS)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    isNewUser: () -> Boolean,
    onSubmitClick: () -> Unit,
) {
    val context = LocalContext.current
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var name by rememberSaveable {
        mutableStateOf("")
    }
    var isPwdVisible by rememberSaveable {
        mutableStateOf(false)
    }
    val maxChar = 10
    var password by rememberSaveable {
        mutableStateOf("")
    }

    val isValid by remember {
        derivedStateOf {
            isValidEmail(email) && isValidPasswordFormat(password)
                    && (if (isNewUser()) isValidName(name) else true)
        }
    }
    val msg =
        "Password is Wrong!\n" +
                "Please enter at least 1 digit, 1 upper case and lowercase letter, 1 special character, no white spaces, at least 8 character"

    Column(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedContent(targetState = isNewUser(), label = "") {
            Text(
                if (it) "Let's make life stylish" else "Welcome Back",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )
        }
        Text(
            "Enter your details below",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Light
        )
        AnimatedContent(targetState = isNewUser(), label = "flowRow") {
            FlowRow(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (it) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        textStyle = TextStyle(fontSize = 16.sp),
                        placeholder = {
                            Text("Enter name", color = Color.Gray)
                        },
                        supportingText = {
                            if (!isValidName(name) && name.isNotEmpty()) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Name is wrong!",
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        },
                        isError = name.isNotEmpty() && !isValidName(name),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                    )
                }
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    modifier = Modifier.weight(1f),
                    textStyle = TextStyle(fontSize = 16.sp),
                    placeholder = {
                        Text("Enter email", color = Color.Gray)
                    },
                    shape = RoundedCornerShape(12.dp),
                    supportingText = {
                        if (!isValidEmail(email) && email.isNotEmpty()) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Email is wrong!",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    },
                    isError = email.isNotEmpty() && !isValidEmail(email),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        if (it.length <= maxChar) password = it
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    visualTransformation =
                    if (isPwdVisible)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                isPwdVisible = !isPwdVisible
                            }
                        ) {
                            Icon(
                                if (isPwdVisible)
                                    painterResource(id = R.drawable.ic_visibility_off)
                                else
                                    painterResource(id = R.drawable.ic_visibility),
                                contentDescription = null
                            )
                        }
                    },
                    supportingText = {
                        if (!isValidPasswordFormat(password) && password.isNotEmpty()) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = msg,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    },
                    textStyle = TextStyle(fontSize = 16.sp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done),
                    placeholder = {
                        Text("Enter password", color = Color.Gray)
                    },
                    isError = password.isNotEmpty() && !isValidPasswordFormat(password),
                )
            }
        }
        Button(
            onClick = {
                if (isValid) {
                    onSubmitClick()
                } else {
                    Toast.makeText(context, "Please enter all valid details", Toast.LENGTH_SHORT)
                        .show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .drawBehind {
                    drawRect(
                        Brush.linearGradient(
                            colors = listOf(
                                Color.Blue,
                                Color.Magenta
                            )
                        )
                    )
                },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                if (isNewUser()) "Sign up" else "Sign in",
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        AnimatedVisibility(!isNewUser()) {
            TextButton(onClick = {
                //forgot password
            }) {
                Text("Forgot your password?")
            }
        }
        Row(
            Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(Modifier.weight(1f))
            AnimatedContent(targetState = isNewUser(), label = "dg") {
                Text(
                    if (it) {
                        "Or sign up with"
                    } else {
                        "Or sign in with"
                    },
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
            HorizontalDivider(Modifier.weight(1f))
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            OutlinedButton(onClick = { /*TODO*/ }, shape = RoundedCornerShape(8.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "google",
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Google")
            }
            OutlinedButton(onClick = { /*TODO*/ }, shape = RoundedCornerShape(8.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_facebook),
                    contentDescription = "Facebook",
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Facebook")
            }
        }
        Spacer(Modifier.weight(1f))
    }
}