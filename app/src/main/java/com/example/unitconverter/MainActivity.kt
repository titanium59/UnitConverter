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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.setValue
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

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("0.00") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var isInputExpanded by remember { mutableStateOf(false) }
    var isOutputExpanded by remember { mutableStateOf(false) }
    var conversionFactor by remember { mutableDoubleStateOf(1.00) }
    var oConversionFactor by remember { mutableDoubleStateOf(1.00) }

    //conversion logic

    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0 //elvis operator
        val result = (inputValueDouble * conversionFactor * 100.0 / oConversionFactor).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //all ui elements stacked in a column
        Text(
            text = "Unit Converter",
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                // Function to be called when value changes
                inputValue = it
                convertUnits()
            },
            label = { Text(text = "Enter Value")}
            )
        Spacer(modifier = Modifier.height(16.dp))
        Row{
            //Input Box
            Box{
                //Input Button
                Button(onClick = { isInputExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(
                    expanded = isInputExpanded,
                    onDismissRequest = {isInputExpanded = false}
                ) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = {
                            isInputExpanded = false
                            inputUnit = "Centimeters"
                            conversionFactor = 0.01
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Meters") },
                        onClick = {
                            isInputExpanded = false
                            inputUnit = "Meters"
                            conversionFactor = 1.0
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            isInputExpanded = false
                            inputUnit = "Feet"
                            conversionFactor = 0.3048
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters") },
                        onClick = {
                            isInputExpanded = false
                            inputUnit = "Millimeters"
                            conversionFactor = 0.001
                            convertUnits()
                        })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            //Output Box
            Box{
                //Output Button
                Button(onClick = { isOutputExpanded = true}) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = isOutputExpanded, onDismissRequest = { isOutputExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = {
                            isOutputExpanded = false
                            outputUnit = "Centimeters"
                            oConversionFactor = 0.01
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Meters") },
                        onClick = {
                            isOutputExpanded = false
                            outputUnit = "Meters"
                            oConversionFactor = 1.00
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            isOutputExpanded = false
                            outputUnit = "Feet"
                            oConversionFactor = 0.3048
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters") },
                        onClick = {
                            isOutputExpanded = false
                            outputUnit = "Millimeters"
                            oConversionFactor = 0.001
                            convertUnits()
                        })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result : $outputValue $outputUnit",
                style = MaterialTheme.typography.headlineSmall
            )

    }

}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}