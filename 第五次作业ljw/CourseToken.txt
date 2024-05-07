package com.example.courseroller


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courseroller.ui.theme.CourseRollerTheme

@Composable
fun TokenLayout(modifier: Modifier=Modifier.fillMaxSize()){
    var amountInput by remember { mutableStateOf("")}
    var amount=amountInput.toDoubleOrNull()?: 0.0
    var token=calculateToken(amount)

   Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
       horizontalAlignment = Alignment.CenterHorizontally,
       verticalArrangement = Arrangement.Center
   ){
        Text(
            text=stringResource(R.string.calculate_token),
            modifier= Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
       EditNumberField(
           value=amountInput,
           onValueChange={ amountInput = it},
           modifier= modifier
               .padding(bottom = 32.dp)
               .fillMaxWidth()
       )
       Text(
           text=stringResource(R.string.token_amount, token),
       )
       Spacer(modifier = Modifier.height(150.dp))
   }
}
@Composable
fun EditNumberField(
    value:String,
    onValueChange:(String) -> Unit,
    modifier: Modifier=Modifier
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = {
            Text(text = stringResource(id = R.string.need_token))
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions( keyboardType= KeyboardType.Number)
    )

}
private fun calculateToken(amount:Double,TokenPercent: Double=105.8):String{
    val token=TokenPercent / 100*amount
    return token.toString()
}
@Composable
@Preview(showBackground = true)
fun TokenApp(){
    CourseRollerTheme {
        TokenLayout()
    }
}