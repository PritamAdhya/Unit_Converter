package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
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
                    unitconverter()
                }
            }
        }
    }
}
@Composable
fun unitconverter(){
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputunit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpected by remember { mutableStateOf(false) }
    var oExpected by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.00) }
    val oConversionFactor= remember { mutableStateOf( 1.00) }
    val customTextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif, // Replace with your desired font family
        fontSize = 32.sp, // Replace with your desired font size
        color = Color.Black // Replace with your desired text color
    )
    fun convertUnit(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result =(inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() /100.0
        outputValue=result.toString()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "UNIT CONVERTER" ,style =customTextStyle)
        Spacer(modifier=Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {inputValue=it
            convertUnit()},
            label = {Text("Enter Value ")})
        Spacer(modifier=Modifier.height(16.dp))
        Row {
            Box {
               Button(onClick = { iExpected = true }) {
                   Text(inputunit)
                   Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                   DropdownMenu(expanded = iExpected, onDismissRequest = { iExpected=false }) {
                       DropdownMenuItem(text = { Text("Centimeters")},
                           onClick = { iExpected=false
                               inputunit="Centimeters"
                                conversionFactor.value=0.01
                                convertUnit()})
                       DropdownMenuItem(text = { Text("Meter")}, onClick = {
                           iExpected=false
                           inputunit="Meters"
                           conversionFactor.value=1.0
                           convertUnit()
                       })
                       DropdownMenuItem(text = { Text("Feet")}, onClick = {
                           iExpected=false
                           inputunit="Feet"
                           conversionFactor.value=0.3048
                           convertUnit()
                       })
                       DropdownMenuItem(text = { Text("Milimeters")}, onClick = {
                           iExpected=false
                           inputunit="Milimeters"
                           conversionFactor.value=0.001
                           convertUnit()
                       })
                   }
               }
            }
            Spacer(modifier = Modifier.width(18.dp))
            Box{
                Button(onClick = { oExpected=true}) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown,contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpected, onDismissRequest = { oExpected=false }) {
                    DropdownMenuItem(text = { Text("Centimeters")}, onClick = {
                        oExpected=false
                        outputUnit="Centimeters"
                        oConversionFactor.value=0.01
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text("Meter")}, onClick = {
                        oExpected=false
                        outputUnit="Meters"
                        oConversionFactor.value=1.00
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text("Feet")}, onClick = {
                        oExpected=false
                        outputUnit="Feet"
                        oConversionFactor.value=0.3048
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text("Milimeters")}, onClick = {
                        oExpected=false
                        outputUnit="Milimeters"
                        oConversionFactor.value=0.001
                        convertUnit()
                    })
                }
            }
        }
        Spacer(modifier=Modifier.height(16.dp))
        Text(text = "Result : $outputValue $outputUnit", style =MaterialTheme.typography.headlineMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun unitconverterPreview() {
    unitconverter()
}