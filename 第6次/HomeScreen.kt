import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.zxy.R
import com.example.zxy.data.User
import com.example.zxy.ui.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route="home"
    override val titleRes = "Inventory"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSrccen(
    navigataToItemEntry:() -> Unit,
    navigataToItemUpdata:(Int) -> Unit,
    modifier:Modifier=Modifier
){
    val scrollBehavior = TooltipDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            InventoryTopAppBar(
                title = stringResource(HomeDestination.titleRes),
                canNavigataBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigataToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = modifier.padding(dimensionResource(id = R.dimen.pa))
            ) {
               Icon(
                   imageVector = Icons.Default.Add,
                   contentDescription = stringResource(R.string.xtt)
               )
            }
        },
    ){innerPadding ->
        HomeBoby(
            itemList = listOf(),
            onItemClick =navigataToItemUpdata,
            modifier = modifier.fillMaxSize(),
            contentPadding = innerPadding,
        )
    }
}
@Composable
private fun HomeBoby(
    itemList: List<User>,
    onItemClick:(Int) -> Unit,
    modifier: Modifier= Modifier,
    contentPadding : PaddingValues = PaddingValues(0.dp),
){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ){
        if(itemList.isEmpty()){
            Text(
                text = stringResource(R.string.xtt),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(contentPadding),
            )
        }else{
            InventoryList(
                itemList = itemList,
                onItemClick = { onItemClick(it.id)},
                contentPadding = contentPadding,
                modifier = Modifier.run { padding(horizontal = dimensionResource(id = 8dp)) }
            )
        }
    }
}