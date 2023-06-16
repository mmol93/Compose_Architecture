package com.example.compose_architecture.ui.screen.views

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.compose_architecture.R
import com.example.compose_architecture.model.screen.ViewScreens
import com.example.compose_architecture.ui.theme.Compose_ArchitectureTheme
import com.example.compose_architecture.util.showToast
import kotlinx.coroutines.launch

object ViewScreen {
    /**
     * ViewScreen 표시
     * */
    @Composable
    fun ShowViewScreen(mainActivityNavHostController: NavHostController) {
        LazyColumn {
            items(count = ViewScreens.values().size) { index ->
                Text(
                    text = ViewScreens.values()[index].name,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .clickable {
                            mainActivityNavHostController.navigate(ViewScreens.values()[index].name)
                        }
                        .padding(20.dp)
                        .fillMaxWidth()
                )
                Divider(color = Color.Gray, thickness = 4.dp)
            }
        }
    }

    /**
     * 컴포즈의 ImageView에 대한 예시(둥근 이미지 만들기, 테두리 넣기 등)
     * 참조: https://developer.android.com/jetpack/compose/graphics/images/customize?hl=ko
     * */
    @Composable
    fun ShowImageView() {
        val spacerHeight = 20.dp
        val imageUrl =
            "https://store-images.microsoft.com/image/apps.21169.9007199266244427.cc23e1b0-9845-4273-918c-f8dbdb058401.ebc29770-cc7b-4af1-89cc-2085c1498f24"
        Compose_ArchitectureTheme {
            val rainbowColorsBrush = remember {
                Brush.sweepGradient(
                    listOf(
                        Color(0xFF9575CD),
                        Color(0xFFBA68C8),
                        Color(0xFFE57373),
                        Color(0xFFFFB74D),
                        Color(0xFFFFF176),
                        Color(0xFFAED581),
                        Color(0xFF4DD0E1),
                        Color(0xFF9575CD)
                    )
                )
            }

            val scrollState = rememberScrollState()
            Column(modifier = Modifier.verticalScroll(scrollState)) {
                // 둥근 이미지 만들기
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        // 원형으로 만들기
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.height(spacerHeight))

                // 라운딩 처리된 이미지 만들기
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        // 라운딩 효과
                        .clip(shape = RoundedCornerShape(16.dp))
                )

                Spacer(modifier = Modifier.height(spacerHeight))

                // 둥근 테두리 씌우기
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(CircleShape)
                        // 테두리 생성
                        .border(BorderStroke(4.dp, rainbowColorsBrush), CircleShape)
                )

                Spacer(modifier = Modifier.height(spacerHeight))

                // 특정 비율의 이미지 만들기
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(CircleShape)
                        // 16:9 비율로 만들기
                        .aspectRatio(16f / 9f)
                )

                Spacer(modifier = Modifier.height(spacerHeight))

                // 이미지에 모자이크 처리하기
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(CircleShape)
                        // 이미지에 모자이크 효과를 넣는다.
                        .blur(
                            // 숫자가 높을수록 강한 모자이크가 들어간다.
                            radiusX = 20.dp,
                            radiusY = 20.dp,
                            edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
                        )
                )
            }
        }
    }

    /**
     * 컴포즈의 Material2를 사용한 Drawer 만들기
     * 디자인 가이드: https://m3.material.io/components/navigation-drawer/guidelines
     * 개발 가이드: https://developer.android.com/jetpack/compose/layouts/material?hl=ko
     * */
    @Composable
    fun ShowDrawerM2() {
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()
        val drawerItemList = listOf("내 정보", "게임", "설정")
        val drawerIconList = listOf(
            R.drawable.baseline_supervised_user_circle_24,
            R.drawable.baseline_videogame_asset_24,
            R.drawable.baseline_settings_24
        )
        // drawer를 사용하기 위해서 Scaffold를 사용하지 않아도 되지만 사용하는 것이 좋다.
        Scaffold(
            scaffoldState = scaffoldState,
            // Drawer를 만드는 방법은 다른 뷰를 만드는 것과 거의 동일함
            drawerContent = {
                Text("Drawer title", modifier = Modifier.padding(16.dp))
                Divider()
                // LazyColum을 사용해서 만들면 혹시 휴대폰이 작아서 모든 메뉴가 표시되지 않더라도 스크롤 할 수 있다.
                LazyColumn {
                    items(count = drawerItemList.size) { index ->
                        Row(
                            modifier = Modifier.clickable { },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = drawerIconList[index]),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(64.dp)
                                    .padding(8.dp)
                            )
                            Text(
                                text = drawerItemList[index],
                                style = MaterialTheme.typography.headlineMedium,
                                modifier = Modifier
                                    .padding(20.dp)
                                    .fillMaxWidth()
                            )
                        }
                    }
                }
            },
            // 제스처로 draw를 open / close 할 수 있는 기능은 켜고 끌 수 있다
            drawerGesturesEnabled = true
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.apply {
                            // drawer의 open/close는 비동기로 진행되야한다.
                            if (isClosed) open() else close()
                        }
                    }
                }) {
                    Text(text = "open / close Drawer")
                }
            }
        }
    }

    /**
     * 컴포즈의 Material3를 사용한 Drawer 만들기
     * 디자인 가이드: https://m3.material.io/components/navigation-drawer/guidelines
     * 개발 가이드: https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#ModalNavigationDrawer(kotlin.Function0,androidx.compose.ui.Modifier,androidx.compose.material3.DrawerState,kotlin.Boolean,androidx.compose.ui.graphics.Color,kotlin.Function0)
     * */
    @Composable
    fun ShowDrawerM3() {
        // 둘 다 Material3를 import한 함수임에 주의
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val drawerItemList = listOf("내 정보", "게임", "설정")
        val drawerIconList = listOf(
            R.drawable.baseline_supervised_user_circle_24,
            R.drawable.baseline_videogame_asset_24,
            R.drawable.baseline_settings_24
        )
        val selectedItem = remember { mutableStateOf(drawerItemList[0]) }

        // NavigarionDrawer가 최상위 View가 된다.(Material3 임에 주의)
        // Drawer를 만드는 방법은 Material2의 drawer와 거의 동일하다.
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                // ModalDrawerSheet에 modifier를 정의해서 drawer의 크기를 조절할 수 있다.
                ModalDrawerSheet(modifier = Modifier.width(250.dp)) {
                    Spacer(Modifier.height(12.dp))
                    drawerItemList.forEachIndexed { index, drawerItemTitle ->
                        // drawer의 각 아이템 정의
                        NavigationDrawerItem(
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                            icon = {
                                Icon(
                                    painter = painterResource(id = drawerIconList[index]),
                                    contentDescription = null
                                )
                            },
                            label = {
                                Text(
                                    text = drawerItemTitle,
                                    style = MaterialTheme.typography.headlineSmall,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                            },
                            // 현재 선택된 아이템을 지정해줘야한다.
                            selected = drawerItemTitle == selectedItem.value,
                            // drawer의 각 아이템을 클릭했을 때 동작
                            onClick = {
                                scope.launch { drawerState.close() }
                                selectedItem.value = drawerItemTitle
                            }
                        )
                    }
                }
            },
            // content 안에 Column 등을 사용해서 그 밖의 View 들을 정의한다.
            // 즉, Layer 상으로 Drawer(=ModalNavigationDrawer)가 제일 위에 있고 그 아래에 다른 view들이 존재한다.
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = if (drawerState.isClosed) ">>> Swipe >>>" else "<<< Swipe <<<")
                    Spacer(Modifier.height(20.dp))
                    // 외부에서 drawerState를 사용하여 drawer를 열고 닫을 수 있다.
                    Button(onClick = { scope.launch { drawerState.open() } }) {
                        Text("Click to open")
                    }
                }
            }
        )
    }

    /**
     * Material3의 Scaffold를 사용한 TopBar 만들기
     * 디자인 가이드: https://m3.material.io/components/top-app-bar/guidelines
     * 개발 가이드: https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#CenterAlignedTopAppBar(kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Function0,kotlin.Function1,androidx.compose.foundation.layout.WindowInsets,androidx.compose.material3.TopAppBarColors,androidx.compose.material3.TopAppBarScrollBehavior)
     * */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ShowTopAppBar(navHostController: NavHostController) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "Centered TopAppBar",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    // flutter와 다르게 stack이 있어도 자동으로 뒤로가기 아이콘이 붙진 않는다.
                    navigationIcon = {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            navHostController.navigate(ViewScreens.Temp.name)
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                )
            },
            content = { innerPadding ->
                LazyColumn(
                    contentPadding = innerPadding,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val list = (0..75).map { it.toString() }
                    items(count = list.size) {
                        Text(
                            text = list[it],
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        )
    }

    /**
     * Navigate시 사용되는 임시 화면
     * */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ShowTempScreen() {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "Temp Screen",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                )
            },
            content = { innerPadding ->
                LazyColumn(
                    contentPadding = innerPadding,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val list = (0..75).map { it.toString() }
                    items(count = list.size) {
                        Text(
                            text = list[it],
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        )
    }

    /**
     * Collapsing 가능한 TopAppBar 구현
     * */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ShowCollapsingTopAppBar() {
        /**
         * 주로 다음 옵션 2가지가 사용된다.
         * 1. enterAlwaysScrollBehavior - 위로 스크롤하면 바로 다시 TopAppBar가 나온다.
         * 2. exitUntilCollapsedScrollBehavior - 제일 위까지 스크롤해야만 다시 TopAppBar가 나온다.
         *
         * 둘 다 아래로 스크롤하면 TopAppBar가 사라지는 공통 기능을 갖고 있다.
         * */
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    // scrollBehavior을 추가한다.
                    scrollBehavior = scrollBehavior,
                    title = {
                        Text(
                            "Temp Screen",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                )
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            content = { innerPadding ->
                LazyColumn(
                    contentPadding = innerPadding,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val list = (0..75).map { it.toString() }
                    items(count = list.size) {
                        Text(
                            text = list[it],
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        )
    }

    /**
     * 컴포즈의 TextView 및 EditTextView를 구현해본다.
     * 개발 가이드: https://developer.android.com/jetpack/compose/text?hl=ko
     * */
    @Composable
    fun ShowTextViews(context: Context) {
        val spacer = 20.dp

        // 테스트용 URL을 담고 있는 텍스트
        val annotatedText = buildAnnotatedString {
            append("Click ")

            // 해당 텍스트(annotation)을 URL로써 다음 텍스트(here)에 등록한다.
            // 다음 텍스트의 기준은 pop()이 나오기 전까지의 모든 텍스트임
            pushStringAnnotation(tag = "URL", annotation = "https://developer.android.com")
            withStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("here")
            }
            pop()
        }

        Column(modifier = Modifier.padding(16.dp)) {
            // resource로 텍스트 지정 + 간단한 textStyle 지정하기
            // FontFamily를 사용해서 프로젝트 내에 있는 폰트 파일 사용 가능 - https://developer.android.com/jetpack/compose/text?hl=ko#fonts
            Text(text = "텍스트 지정 및 스타일(색, 크기, 스타일) 지정", fontSize = 12.sp)
            Text(
                text = stringResource(id = R.string.test_text_string),
                color = Color.Gray,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(spacer))

            // 텍스트 정렬 - 해당 뷰의 공간에 대해 정렬을 실시한다. 그렇기 때문에 fillMaxWidth 필요(부모 컨테이너 뷰에 대한 정렬 아님)
            Text(text = "텍스트 뷰 안에서 텍스트 정렬", fontSize = 12.sp)
            Text(
                text = stringResource(id = R.string.test_text_string),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(spacer))

            // 동일한 Text에 서로 다른 Style 적용하기
            Text(text = "하나의 텍스트에 텍스트별로 여러 스타일 지정하기", fontSize = 12.sp)
            Text(buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append("H")
                }
                append("ello ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
                    append("W")
                }
                append("orld")
            })
            Spacer(modifier = Modifier.height(spacer))

            // 텍스트 줄 수 제한하기
            Text(text = "텍스트 줄 수 제어하기", fontSize = 12.sp)
            Text(text = "repeat ".repeat(25), maxLines = 2, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(spacer))

            // 유저가 복사 가능한 텍스트 - 기본적으로 Text는 선택할 수 없다.
            Text(text = "사용자가 선택 가능한(복사 등) 텍스트로 만들어주기", fontSize = 12.sp)
            SelectionContainer {
                Column {
                    Text("This text is selectable")
                    Spacer(modifier = Modifier.height(4.dp))

                    // 그 중에서 일부분은 선택할 수 없는 텍스트를 만들 수 있음
                    DisableSelection {
                        Text("This text is not selectable")
                    }
                }
            }
            Spacer(modifier = Modifier.height(spacer))

            // URL 태그를 갖는 annotation 텍스트만 가져와서 해당 텍스트를 클릭할 경우 동작하게 한다.
            // 즉, 기존 textView에 span을 사용해서 특정 부분만 클릭할 수 있게 한거랑 비슷한 기능.
            Text(text = "기존의 textSpan 처럼 텍스트 일부분을 클릭할 수 있게 만들기", fontSize = 12.sp)
            ClickableText(
                text = annotatedText,
                onClick = { offset ->
                    // We check if there is an *URL* annotation attached to the text
                    // at the clicked position
                    annotatedText.getStringAnnotations(tag = "URL", start = offset, end = offset)
                        .firstOrNull()?.let { annotation ->
                            // If yes, it will show Toast
                            showToast(context = context, text = annotation.item)
                        }
                }
            )
            Spacer(modifier = Modifier.height(spacer))

            // import androidx.compose.runtime.setValue for remember
            // import androidx.compose.runtime.getValue for remember
            var text by remember { mutableStateOf("Test Text") }
            // 채워지지 않은 TextField
            // 채워진건 그냥 TextField
            Text(text = "입력할 수 있는 에디트 텍스트", fontSize = 12.sp)
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Label") }
            )
            Spacer(modifier = Modifier.height(spacer))

            // 입력 형식 지정 (예: 비밀번호 입력)
            // rememberSaveable: 앱을 종료하더라고 해당 값을 유지하고 있는다.
            var password by rememberSaveable { mutableStateOf("") }
            Text(text = "에디트 텍스트에 입력 형식 지정하기", fontSize = 12.sp)
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Enter password") },
                visualTransformation = PasswordVisualTransformation(),
                // 더 많은 입력 양식은 여기 - https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:compose/ui/ui-text/samples/src/main/java/androidx/compose/ui/text/samples/VisualTransformationSamples.kt?hl=ko
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.height(spacer))

            // 입력 받은 문자열을 변환
            var inputTrimStart by remember { mutableStateOf("") }
            Text(text = "에디트 텍스트에 입력할 수 있는 문자열 제어하기", fontSize = 12.sp)
            TextField(
                value = inputTrimStart,
                onValueChange = { newText ->
                    // 제일 처음 오는 문자열로 0을 사용할 수 없음
                    inputTrimStart = newText.trimStart { it == '0' }
                }
            )
            var inputTrim by remember { mutableStateOf("") }
            TextField(
                value = inputTrim,
                onValueChange = { newText ->
                    // 문자 0 을 사용할 수 없음. 사용할 경우 Toast 보여줌
                    inputTrim = newText.trim {
                        if (it == '0') {
                            showToast(context = context, text = "you can't put '0'")
                            true
                        } else false
                    }
                }
            )
            Spacer(modifier = Modifier.height(spacer))
        }
    }

    /**
     * 기본적인 BottomSheet 사용 방법
     * M3에서 BottomSheet를 생성하는 방법은 두 가지가 있다.
     * 1. BottomSheetScaffold - Scaffold 안에 이미 BottomSheet를 만들 수 있는 옵션이 있기 때문에 간다하게 만들 수 있고 코드도 더 짧다. 대신 해당 BottomSheetScaffold을 반드시 사용해야하는 조건이 붙음
     * 2. ModalBottomSheet - 단순히 하나의 View나 Layout으로써 BottomSheet를 만들 수 있다.(즉, 독립적) 대신 BottomSheetScaffold에 비해 코드가 길고 복잡해진다.
     * 여기서는 독립적으로 사용할 수 있는 ModalBottomSheet을 사용해서 정의한다.
     *
     * 개발 가이드: https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#ModalBottomSheet(kotlin.Function0,androidx.compose.ui.Modifier,androidx.compose.material3.SheetState,androidx.compose.ui.graphics.Shape,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.unit.Dp,androidx.compose.ui.graphics.Color,kotlin.Function0,kotlin.Function1)
     * */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ShowBottomSheetView() {
        // BottomSheet on/off 상태 저장용
        var openBottomSheet by rememberSaveable { mutableStateOf(false) }
        var skipPartiallyExpanded by remember { mutableStateOf(false) }
        val scope = rememberCoroutineScope()
        // BottomSheet 상태 저장
        val bottomSheetState = rememberModalBottomSheetState(
            // skipPartiallyExpanded on일 경우 BottomSheet가 화면 전체를 차지하게 펼쳐진다(한 번 접을 수 없고 내리면 완전히 숨겨진다)
            skipPartiallyExpanded = skipPartiallyExpanded
        )
        // BottomSheet를 보여주기 위해 필요한 View를 셋팅하기 위한 Column
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                Modifier.toggleable(
                    value = skipPartiallyExpanded,
                    role = Role.Checkbox,
                    onValueChange = { checked -> skipPartiallyExpanded = checked }
                )
            ) {
                Checkbox(checked = skipPartiallyExpanded, onCheckedChange = null)
                Spacer(Modifier.width(16.dp))
                Text("BottomSheet를 화면 가득히 열기")
            }
            Button(onClick = { openBottomSheet = !openBottomSheet }) {
                Text(text = "Show Bottom Sheet")
            }
        }
        // BottomSheet 제어 - 즉, openBottomSheet가 바뀔 때마다 ModalBottomSheet 다시 그린다(?)
        if (openBottomSheet) {
            ModalBottomSheet(
                // BottomSheet가 dismiss되 었을 때 어떤 행동을 할지 정의
                onDismissRequest = { openBottomSheet = false },
                // BottomSheet의 열고 닫기 상태 정의
                sheetState = bottomSheetState,
            ) {
                // ModalBottomSheet 안에 어떤 형태(View, Layout)로 넣을지를 정의한다.
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        // 위에 있는 onDismissRequest 안에서 BottomSheet를 숨기는 처리를 하지 않고 그 밖에서도 하고싶다면 아래와 같이 ButtomSheetState를 지정해줘야한다.
                        onClick = {
                            scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                                if (!bottomSheetState.isVisible) {
                                    openBottomSheet = false
                                }
                            }
                        }
                    ) {
                        Text("Hide Bottom Sheet")
                    }
                }
                var text by remember { mutableStateOf("") }
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = text,
                    onValueChange = { text = it })
                LazyColumn {
                    items(50) {
                        ListItem(
                            headlineContent = { Text("Item $it") },
                            leadingContent = {
                                Icon(
                                    Icons.Default.Favorite,
                                    contentDescription = "Localized description"
                                )
                            }
                        )
                    }
                }
            }
        }
    }

    /**
     * 각 종 dialog를 출력해본다
     * 1. 일반적인 버튼 클릭을 할 수 있는 dialog - 과거 AlertDialog에는 버튼을 3개까지 설정할 수 있었지만 지금은 2개만 설정가능
     * 2. 특정 날짜를 선택할 수 있는 dialog
     * 3. 특정 범위의 날짜를 선택할 수 있는 dialog
     * */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ShowDialog() {
        val openAlertDialog = remember { mutableStateOf(false) }
        // 메인 아이콘 하나를 갖고 있는 AlertDialog
        if (openAlertDialog.value) {
            AlertDialog(
                // 사용자가 dialog 밖을 눌렀거나 뒤로가기 버튼을 눌렀을 경우 아래 내용이 실시된다.
                onDismissRequest = {
                    openAlertDialog.value = false
                },
                // dialog의 윗 부분에 삽입될 Icon
                icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                // dialog의 타이틀
                title = {
                    Text(text = "Title")
                },
                // dialog의 텍스트
                text = {
                    Text(
                        "This area typically contains the supportive text " +
                                "which presents the details regarding the Dialog's purpose."
                    )
                },
                // 확인 버튼(오른쪽에 위치)
                confirmButton = {
                    TextButton(
                        onClick = {
                            openAlertDialog.value = false
                        }
                    ) {
                        Text("Confirm")
                    }
                },
                // 가부 버튼(왼쪽에 위치)
                dismissButton = {
                    TextButton(
                        onClick = {
                            openAlertDialog.value = false
                        }
                    ) {
                        Text("Dismiss")
                    }
                }
            )
        }

        // ------------- DateDialog -------------------
        // 클릭하여 받은 날짜 데이터를 표시하기 위해 스낵바 사용
        val snackStateForDateDialog = remember { SnackbarHostState() }
        val snackScopeForDateDialog = rememberCoroutineScope()
        SnackbarHost(hostState = snackStateForDateDialog, Modifier)

        val openDateDialog = remember { mutableStateOf(false) }

        // 날짜를 하나 선택할 수 있는 DatePickerDialog
        if (openDateDialog.value) {
            val datePickerState = rememberDatePickerState()
            // derivedStateOf을 사용해서 { } 안의 값이 변할 때마다 추적하고 UI를 바뀌게(remember) 하기.
            val confirmEnabled by remember { derivedStateOf { datePickerState.selectedDateMillis != null } }
            DatePickerDialog(
                // 사용자가 dialog 밖을 눌렀거나 뒤로가기 버튼을 눌렀을 경우 아래 내용이 실시된다.
                onDismissRequest = {
                    openDateDialog.value = false
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            openDateDialog.value = false
                            snackScopeForDateDialog.launch {
                                snackStateForDateDialog.showSnackbar(
                                    "Selected date timestamp: ${datePickerState.selectedDateMillis}"
                                )
                            }
                        },
                        enabled = confirmEnabled
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            openDateDialog.value = false
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        // ------------- DateRangePicker -------------------
        val snackStateForDateRangeDialog = remember { SnackbarHostState() }
        val snackScopeForDateRangeDialog = rememberCoroutineScope()
        SnackbarHost(hostState = snackStateForDateRangeDialog, Modifier)

        val bottomSheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        )

        val dateRangePickerState = rememberDateRangePickerState()

        val openDateRangeDialog = remember { mutableStateOf(false) }

        if (openDateRangeDialog.value) {
            // DateRangePicker는 Dialog가 아니기 때문에 dialog 처럼 보이게 하기 위해 BottomSheet 안에 넣어서 정의하는 것이 좋다.
            ModalBottomSheet(
                // BottomSheet가 dismiss되 었을 때 어떤 행동을 할지 정의
                onDismissRequest = { openDateRangeDialog.value = false },
                // BottomSheet의 열고 닫기 상태 정의
                sheetState = bottomSheetState,
            ) {
                // 아래의 Column에 들어있는 모든 widget이 DateRangePicker에서 사용될 widget들이다.
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
                    // Add a row with "Save" and dismiss actions.
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(onClick = { openDateRangeDialog.value = false }) {
                            Icon(Icons.Filled.Close, contentDescription = "Localized description")
                        }
                        TextButton(
                            onClick = {
                                openDateRangeDialog.value = false
                                snackScopeForDateRangeDialog.launch {
                                    snackStateForDateRangeDialog.showSnackbar(
                                        "Saved range (timestamps): " + "${dateRangePickerState.selectedStartDateMillis!!..dateRangePickerState.selectedEndDateMillis!!}"
                                    )
                                }
                            },
                            enabled = dateRangePickerState.selectedEndDateMillis != null
                        ) {
                            Text(text = "Save")
                        }
                    }
                    // DateRangePicker는 하나의 Widget이기 때문에 DateDialog 처럼 Dialog 형태로 출력되지 않는다.
                    DateRangePicker(
                        state = dateRangePickerState,
                        modifier = Modifier.weight(1f),
                        // showModeToggle를 true로 할 경우 사용자가 직접 입력으로 설정할 수 있게된다.
                        showModeToggle = true
                    )
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { openAlertDialog.value = !openAlertDialog.value }) {
                Text(text = "show Alert Dialog")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { openDateDialog.value = !openDateDialog.value }) {
                Text(text = "show Date Dialog")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { openDateRangeDialog.value = !openDateRangeDialog.value }) {
                Text(text = "show Date Range Dialog")
            }
        }
    }
}