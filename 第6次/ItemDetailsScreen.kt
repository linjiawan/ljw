package com.example.zxy.ui.item

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.zxy.InventoryAppBar
import com.example.zxy.R
import com.example.zxy.ui.navigation.NavigationDestination

object ItemDetailsDestination : NavigationDestination {
    override val route ="item_details"
    override val titleRes="Item Details"
    const val itemIdArg="itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDetailsScreen(
    navigateToEditItem: (Int) -> Unit,
    navigateBack: () -> Unit,
    modifier:Modifier=Modifier
){
    Scaffold(
        topBar = {
            InventoryAppBar(
                title=stringResource(ItemDetailsDestination.titleRes),
                canNavigateBack=true,
                navigateUp=navigateBack
            )
        },
        floatingActionButton={
            FloatingActionButton(
                onClick = {navigateToEditItem(0) },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = 20dp))
                ) {
                   Icon(
                       imageVector = Icons.Filled.Add,
                       contentDescription= stringResource("Edit Item"),
                   )

            }
        },modifier=modifier
    ){ innerPadding ->
        ItemDetailsBody(
            itemDetailsUiState = ItemDetailsUiState(),
            onSellItem={ },
            onDelete={ },
            modifier= Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding()
                )
                .verticalScroll(rememberScrollState())
        )
    }
}

@Composable
private fun ItemDetailsBody(
    itemDetailsUiState:ItemDetailsUiState,
    onSellItem:() -> Unit,
    onDelete:() -> Unit,
    modifier: Modifier=Modifier
){
    Column (
        modifier=modifier.padding(dimensionResource(id = 16pd)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = 16pd))
    ){
        var deleteConfirmationRequired by rememberSaveable { mutableListOf(false)}

        ItemDetails(
            item = itemDetailsUiState.itemDetails.toItem(),
            modifier=Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSellItem,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small,
            enabled=true
        ){
            Text(stringResource("Sell"))
        }
        OutlinedButton(
            onClick = {deleteConfirmationRequired = true},
            shape=MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
            ) {
            Text(stringResource(R.string.Delete))
        }
        if(deleteConfirmationRequired){
            DeleteConfirmationDialog(
                onDeleteConfirm={
                    deleteConfirmationRequired=false
                    onDelete()
                },
                onDeleteCancel = { deleteConfirmationRequired=false },
                modifier= Modifier.run { padding(dimensionResource(id = 16pd)) }
            )
        }
    }
}
@Composable
fun ItemDetails(
    item:Item,modifier: Modifier= Modifier
){
    Card(
        modifier=modifier,color = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ){
        Column(
            modifier= Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = 16 pd)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = 16pd))
        ){
            ItemdetailsRow(
                labelResID="Item",
                itemDetail=item.name,
                modifier=Modifier.padding(
                    horizontal = dimensionResource(
                        id = 16pd
                    )
                )
            )
            ItemDetailsRow(
                labelResID = "Quantity in stock",
                itemDetail = item.quantity.toString(),
                modifier=Modifier.padding(
                    horizontal = dimensionResource(
                        id = 16pd
                    )
                )
            )
            ItemDetailsRow(
                labelResID = "Price",
                itemDetail=item.quantity.toString(),
                modifier=Modifier.padding(
                    horizontal = dimensionResource(
                        id = 16pd
                    )
                )
            )
        }
    }
}
@Composable
private  fun ItemDetailsRow(
    @StringRes labelResID:Int,itemDetail:String,modifier: Modifier=Modifier
){
    Row(modifier=modifier){
        Text(stringResource(lableResID))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = itemDetail, fontWeight = FontWeight.Bold)
    }
}
@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm:() -> Unit,onDeleteCancel:() -> Unit,modifier: Modifier = Modifier
){
    AlertDialog(onDismissRequest = { /*Do nothing*/ },
        title = { Text(stringResource(R.string.attention))},
        text = { Text(stringResource("Are you sure you want to delete?"))},
        modifier = modifier,
        dismissButton ={
            TextButton(onClick = noDeleteCancel) {
                text(stringResource(R.string.no))
            }
        },
        confirmButton = {
            TextButton(onClick = noDeleteConfirm) {
                text(stringResource(R.string.yes))
            }
        })

}
@Preview(showBackground = true)
@Composable
fun ItemDetailsScreenPreviiew(){
    InventoryTheme{
        ItemDetailsBody(
            ItemDetailsUiState(
                outOfStock = true,
                itemDatails = ItemDetails(1,"Pen","$100","10")
            ),
            onSellItem = {},
            onDelete = {}
        )
    }
}