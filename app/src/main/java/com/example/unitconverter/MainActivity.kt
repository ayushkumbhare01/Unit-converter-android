
package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}
@Composable
fun UnitConverter(){
    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("0.00") }
    var inputunit by remember { mutableStateOf("meters") }
    var outputunit by remember { mutableStateOf("meters") }
    var iexpanded by remember { mutableStateOf(false) }
    var oexpanded by remember { mutableStateOf(false) }
    var conversionfac = remember { mutableStateOf(1.0) }
    var oconversionfac = remember { mutableStateOf(1.0) }

    fun unitconverter(){
        val inputdouble=input.toDoubleOrNull() ?: 0.0
        val result=(inputdouble * conversionfac.value*100.0 / oconversionfac.value).roundToInt()/100.0
        output=result.toString()
    }


    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Text("UNIT CONVERTER", fontSize = 30.sp,)
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(value = input, onValueChange = {input=it
            unitconverter()}, label = { Text(text = "enter value")}, keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
        Spacer(modifier = Modifier.height(32.dp))
        Row {
            //Input box
            Box{
                Button(onClick = {iexpanded=true}, modifier = Modifier.size(148.dp,50.dp)) {
                    Text(text = inputunit)
                    Icon(Icons.Default.KeyboardArrowDown,contentDescription = null)

                }
                DropdownMenu(expanded = iexpanded, onDismissRequest = { iexpanded=false }) {
                    DropdownMenuItem(text = { Text("meters")}, onClick = {
                        iexpanded=false
                        inputunit="meters"
                        conversionfac.value= 1.0
                        unitconverter()
                    })
                    DropdownMenuItem(text = { Text(text = "centimeters")}, onClick = {
                        iexpanded=false
                        inputunit="centimeters"
                        conversionfac.value=0.01
                        unitconverter()
                    })
                    DropdownMenuItem(text = { Text(text = "feet")}, onClick = {
                        iexpanded=false
                        inputunit="feet"
                        conversionfac.value=0.3048
                        unitconverter()
                    })
                    DropdownMenuItem(text = { Text(text = "millimeters")}, onClick = {
                        iexpanded = false
                        inputunit = "millimeters"
                        conversionfac.value = 0.001
                        unitconverter()
                    })
                }
            }
            Spacer(modifier = Modifier.width(35.dp))
            //Output box
            Box{
                Button(onClick = {oexpanded=true},modifier = Modifier.size(148.dp,50.dp)) {
                    Text(text = outputunit)
                    Icon(Icons.Default.KeyboardArrowDown,contentDescription = null)
                }
                DropdownMenu(expanded = oexpanded, onDismissRequest = {oexpanded=false}) {
                    DropdownMenuItem(text = { Text("meters")}, onClick = {
                        oexpanded=false
                        outputunit="meters"
                        oconversionfac.value= 1.0
                        unitconverter()
                    })
                    DropdownMenuItem(text = { Text(text = "centimeters")}, onClick = {
                        oexpanded=false
                        outputunit="centimeters"
                        oconversionfac.value= 0.01
                        unitconverter()
                    })
                    DropdownMenuItem(text = { Text(text = "feet")}, onClick = {
                        oexpanded=false
                        outputunit="feet"
                        oconversionfac.value= 0.3048
                        unitconverter()
                    })
                    DropdownMenuItem(text = { Text(text = "millimeters")}, onClick = {
                        oexpanded=false
                        outputunit="millimeters"
                        oconversionfac.value= 0.001
                        unitconverter()

                    })
                }
            }


        }
        Spacer(modifier = Modifier.height(32.dp))
        Text("Result:$output $outputunit", fontSize = 20.sp )
    }

}



@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    Column {
        UnitConverter()

    }


}