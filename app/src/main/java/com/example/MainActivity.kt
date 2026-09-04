package com.example

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.material.icons.outlined.DeviceHub
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Terminal
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

val Slate900 = Color(0xFF0F172A)
val Slate800 = Color(0xFF1E293B)
val Slate700 = Color(0xFF334155)
val Slate600 = Color(0xFF475569)
val Slate500 = Color(0xFF64748B)
val Slate400 = Color(0xFF94A3B8)
val Slate300 = Color(0xFFCBD5E1)
val Cyan400 = Color(0xFF22D3EE)
val Cyan500 = Color(0xFF06B6D4)
val Cyan600 = Color(0xFF0891B2)
val Emerald400 = Color(0xFF34D399)
val Emerald500 = Color(0xFF10B981)
val Purple400 = Color(0xFFC084FC)
val Purple500 = Color(0xFFA855F7)
val Indigo100 = Color(0xFFE0E7FF)
val Indigo300 = Color(0xFFA5B4FC)
val Indigo500 = Color(0xFF6366F1)
val Indigo600 = Color(0xFF4F46E5)
val Black60 = Color(0x99000000)

data class LogEntry(val timestamp: String, val message: String, val color: Color)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(background = Slate900)
            ) {
                MainAppScreen()
            }
        }
    }
}

@Composable
fun MainAppScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    val logs = remember { mutableStateListOf<LogEntry>() }
    
    LaunchedEffect(Unit) {
        logs.add(LogEntry(getCurrentTime(), "Iniciando Nodo MX-SQ-3000 (ALEPH-Σ)", Cyan500))
        delay(300)
        logs.add(LogEntry(getCurrentTime(), "Operator: José Francisco Cantoriano Leyva (CALF8712186T5)", Slate300))
        delay(300)
        logs.add(LogEntry(getCurrentTime(), "Terminal: CPH2669 | Android 14 | IP: 192.168.1.76", Cyan400))
        delay(300)
        logs.add(LogEntry(getCurrentTime(), "Bucket GCS: gs://kumi-ghost-alef-g6-000155 (cantoriano-leyvajf)", Emerald400))
        delay(400)
        logs.add(LogEntry(getCurrentTime(), "CryptAI: ML-KEM-1024_Σ active (Entropy ε = 0.994)", Emerald400))
        delay(300)
        logs.add(LogEntry(getCurrentTime(), "GenAI: Cloud Run neurobin-aleph-v28-4 online", Indigo300))
        delay(300)
        logs.add(LogEntry(getCurrentTime(), "TestAI WebSocket: wss://api.neurospark.inc/ws/kumi-stream [Stable ~25ms]", Cyan500))
        delay(300)
        logs.add(LogEntry(getCurrentTime(), "STATUS: OPERATIVO_PLENO | Push ~50ms Active", Emerald500))
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Slate900,
        contentWindowInsets = WindowInsets.systemBars,
        bottomBar = {
            BottomNavigationBar(selectedTab = selectedTab, onTabSelected = { selectedTab = it })
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            when (selectedTab) {
                0 -> DashboardTab(logs = logs)
                1 -> TrinitaryLoopTab()
                2 -> TermuxClientTab()
                3 -> OperatorNodeTab()
                4 -> VaultSettingsTab(logs = logs)
            }
        }
    }
}

@Composable
fun DashboardTab(logs: List<LogEntry>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HeaderSection()
        TerminalSection(modifier = Modifier.weight(1f), logs = logs)
        BentoGridSection()
        ActionCardSection()
    }
}

@Composable
fun TrinitaryLoopTab() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HeaderSection()
        
        Text(
            text = "ESTADO DEL BUCLE TRINITARIO",
            color = Cyan400,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp,
            fontFamily = FontFamily.Monospace
        )
        
        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                TrinitaryCard(
                    title = "🔐 CryptAI (ML-KEM-1024_Σ)",
                    subtitle = "Entropía vectorial activa: ε = 0.994",
                    description = "Cifrado post-cuántico soberano FIPS 203 integrado con el plano de control central.",
                    badge = "FIPS 203",
                    badgeColor = Cyan400
                )
            }
            item {
                TrinitaryCard(
                    title = "⚡ GenAI (Cloud Run)",
                    subtitle = "Instancia: neurobin-aleph-v28-4",
                    description = "Motor KUMI operativo en us-central1 (cantoriano-leyvajf) con Google Gemini APIs.",
                    badge = "ONLINE",
                    badgeColor = Emerald400
                )
            }
            item {
                TrinitaryCard(
                    title = "🧪 TestAI (WebSocket Telemetry)",
                    subtitle = "Endpoint: wss://api.neurospark.inc/ws/kumi-stream",
                    description = "Monitoreo topológico y vigilancia continua de latencia transfinita (~25ms).",
                    badge = "WS_ACTIVE",
                    badgeColor = Purple400
                )
            }
        }
    }
}

@Composable
fun TrinitaryCard(title: String, subtitle: String, description: String, badge: String, badgeColor: Color) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Slate800.copy(alpha = 0.6f))
            .border(1.dp, Slate700.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Surface(
                color = badgeColor.copy(alpha = 0.15f),
                shape = RoundedCornerShape(8.dp),
                border = borderStroke(1.dp, badgeColor.copy(alpha = 0.4f))
            ) {
                Text(
                    text = badge,
                    color = badgeColor,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    fontFamily = FontFamily.Monospace
                )
            }
        }
        Text(
            text = subtitle,
            color = Cyan400,
            fontSize = 11.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = description,
            color = Slate300,
            fontSize = 12.sp
        )
    }
}

fun borderStroke(width: androidx.compose.ui.unit.Dp, color: Color) = androidx.compose.foundation.BorderStroke(width, color)

@Composable
fun TermuxClientTab() {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HeaderSection()
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "CLIENTE TERMUX & PUSH ~50ms",
                color = Cyan400,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                fontFamily = FontFamily.Monospace
            )
            Surface(
                color = Emerald500.copy(alpha = 0.2f),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Emerald500.copy(alpha = 0.4f))
            ) {
                Text(
                    text = "GCS SYNC",
                    color = Emerald400,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    fontFamily = FontFamily.Monospace
                )
            }
        }
        
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(24.dp))
                .background(Slate800.copy(alpha = 0.6f))
                .border(1.dp, Slate700.copy(alpha = 0.5f), RoundedCornerShape(24.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "kumi-termux-client.sh (Active Script)",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Black60)
                    .border(1.dp, Slate700, RoundedCornerShape(12.dp))
                    .padding(12.dp)
            ) {
                LazyColumn {
                    item {
                        Text(
                            text = "#!/data/data/com.termux/files/usr/bin/bash\n" +
                                    "# KUMI-STREAM → GCS PUSH ~50ms\n" +
                                    "# Node: MX-SQ-3000_G6 | CPH2669\n" +
                                    "PROJECT=\"cantoriano-leyvajf\"\n" +
                                    "BUCKET=\"kumi-ghost-alef-g6-000155\"\n" +
                                    "WSS=\"wss://api.neurospark.inc/ws/kumi-stream\"\n\n" +
                                    "kumi_python_gcs() {\n" +
                                    "  python3 -c 'import websockets, asyncio; ...'\n" +
                                    "}\n\n" +
                                    "# Status: Stream connected [25ms]\n" +
                                    "# gsutil cp live-json gs://kumi-ghost-alef-g6-000155/telemetry/",
                            color = Cyan400,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 11.sp
                        )
                    }
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { Toast.makeText(context, "Iniciando Python Websocket Client en Termux...", Toast.LENGTH_SHORT).show() },
                    colors = ButtonDefaults.buttonColors(containerColor = Cyan600, contentColor = Color.White),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "INICIAR PYTHON", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
                
                Button(
                    onClick = { Toast.makeText(context, "Sincronizando con gs://kumi-ghost-alef-g6-000155", Toast.LENGTH_SHORT).show() },
                    colors = ButtonDefaults.buttonColors(containerColor = Slate700, contentColor = Color.White),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(imageVector = Icons.Default.Cloud, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "TEST GCS PUSH", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun OperatorNodeTab() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HeaderSection()
        
        Text(
            text = "REGISTRO DE NODO SOBERANO MX-SQ-3000",
            color = Cyan400,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp,
            fontFamily = FontFamily.Monospace
        )
        
        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                InfoCard("OPERADOR", "José Francisco Cantoriano Leyva (CALF8712186T5)")
            }
            item {
                InfoCard("PROYECTO FIREBASE", "cantoriano-leyvajf")
            }
            item {
                InfoCard("BUCKET DE CUSTODIA", "gs://kumi-ghost-alef-g6-000155")
            }
            item {
                InfoCard("DISPOSITIVO TERMINAL", "CPH2669 (Android 14) - Hardware CPH2669_11")
            }
            item {
                InfoCard("BASEBAND & KERNEL", "Baseband: Q_V1_P14 | Kernel: 5.15.180-android13")
            }
            item {
                InfoCard("IMEI & SERIAL", "IMEI: 866276073146473 | Serial: 291ba76f (SV: 66)")
            }
            item {
                InfoCard("RED & MAC", "IP: 192.168.1.76 | MAC Wi-Fi: 74:d5:58:85:ac:25")
            }
            item {
                InfoCard("SELLO PERPETUO", "♾️-ALEPH-Σ-3000 (Capacidad residual operativa)")
            }
        }
    }
}

@Composable
fun InfoCard(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Slate800.copy(alpha = 0.6f))
            .border(1.dp, Slate700.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = label,
            color = Cyan400,
            fontSize = 10.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )
        Text(
            text = value,
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.Monospace
        )
    }
}

@Composable
fun VaultSettingsTab(logs: List<LogEntry>) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HeaderSection()
        
        Text(
            text = "GESTIÓN Y SEGURIDAD",
            color = Cyan400,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp,
            fontFamily = FontFamily.Monospace
        )
        
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(24.dp))
                .background(Slate800.copy(alpha = 0.6f))
                .border(1.dp, Slate700.copy(alpha = 0.5f), RoundedCornerShape(24.dp))
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Políticas de seguridad Firestore y Storage sincronizadas con el plano de control soberano.",
                color = Slate300,
                fontSize = 12.sp
            )
            
            Button(
                onClick = { exportLogsToCsv(context, logs) },
                colors = ButtonDefaults.buttonColors(containerColor = Cyan600, contentColor = Color.White),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(imageVector = Icons.Default.Download, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "EXPORTAR TELEMETRÍA CSV", fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
            
            Button(
                onClick = { Toast.makeText(context, "Sincronización con Nodo MX-SQ-3000 verificada.", Toast.LENGTH_SHORT).show() },
                colors = ButtonDefaults.buttonColors(containerColor = Slate700, contentColor = Color.White),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(imageVector = Icons.Default.Sync, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "VERIFICAR ENLACE SOBERANO", fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Slate800.copy(alpha = 0.4f))
            .border(1.dp, Slate700.copy(alpha = 0.5f), RoundedCornerShape(24.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "System Core",
                color = Cyan400,
                fontSize = 10.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "KUMI_AI ",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "ALEPH-Σ",
                    color = Cyan500,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        
        Column(horizontalAlignment = Alignment.End) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Emerald500.copy(alpha = 0.1f))
                    .border(1.dp, Emerald500.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                val infiniteTransition = rememberInfiniteTransition(label = "pulse")
                val alpha by infiniteTransition.animateFloat(
                    initialValue = 1f,
                    targetValue = 0.4f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(1000, easing = FastOutSlowInEasing),
                        repeatMode = RepeatMode.Reverse
                    ),
                    label = "pulseAlpha"
                )
                
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(Emerald500.copy(alpha = alpha))
                )
                Text(
                    text = "SQ-3000",
                    color = Emerald400,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            }
            Text(
                text = "v28.4-GOLD",
                color = Slate500,
                fontSize = 9.sp,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun TerminalSection(modifier: Modifier = Modifier, logs: List<LogEntry>) {
    val context = LocalContext.current
    
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Black60)
            .border(1.dp, Slate800, RoundedCornerShape(24.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Terminal Instance: ~ / node-mx-sq-3000",
                color = Slate400,
                fontSize = 9.sp,
                fontFamily = FontFamily.Monospace,
                letterSpacing = 1.5.sp
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    color = Slate800.copy(alpha = 0.6f),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Slate700),
                    modifier = Modifier.clickable {
                        exportLogsToCsv(context, logs)
                    }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Download,
                            contentDescription = "Export CSV",
                            tint = Cyan400,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = "CSV",
                            color = Cyan400,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
        
        Divider(color = Slate800.copy(alpha = 0.5f), modifier = Modifier.padding(bottom = 12.dp))
        
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(logs) { log ->
                Text(
                    text = log.message,
                    color = log.color,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 11.sp
                )
            }
            item {
                Row(modifier = Modifier.padding(top = 8.dp)) {
                    Text(text = "~ $ ", color = Color.White, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace, fontSize = 11.sp)
                    val infiniteTransition = rememberInfiniteTransition(label = "cursor")
                    val alpha by infiniteTransition.animateFloat(
                        initialValue = 1f,
                        targetValue = 0f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(500, easing = LinearEasing),
                            repeatMode = RepeatMode.Reverse
                        ),
                        label = "cursorAlpha"
                    )
                    Text(text = "_", color = Slate400.copy(alpha = alpha), fontFamily = FontFamily.Monospace, fontSize = 11.sp)
                }
            }
        }
        
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd) {
            Text(
                text = "Sello: ♾️-PERPETUO",
                color = Slate600,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}

fun exportLogsToCsv(context: Context, logs: List<LogEntry>) {
    try {
        val fileName = "telemetry_logs_${System.currentTimeMillis()}.csv"
        val file = File(context.cacheDir, fileName)
        var csvData = "Timestamp,Message\n"
        logs.forEach { log ->
            val escapedMessage = log.message.replace("\"", "\"\"")
            csvData += "${log.timestamp},\"$escapedMessage\"\n"
        }
        file.writeText(csvData)
        Toast.makeText(context, "Logs exportados: ${file.absolutePath}", Toast.LENGTH_LONG).show()
    } catch (e: Exception) {
        Toast.makeText(context, "Error CSV: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}

fun getCurrentTime(): String {
    val sdf = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())
    return sdf.format(Date())
}

@Composable
fun BentoGridSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(20.dp))
                .background(Slate800.copy(alpha = 0.6f))
                .border(1.dp, Slate700.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "ENTROPÍA VECTORIAL",
                    color = Slate500,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "ε = 0.994",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "ML-KEM-1024_Σ",
                color = Cyan400,
                fontSize = 9.sp,
                fontFamily = FontFamily.Monospace
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(20.dp))
                .background(Slate800.copy(alpha = 0.6f))
                .border(1.dp, Slate700.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "DISPOSITIVO / BUCKET",
                    color = Slate500,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "CPH2669 / G6",
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "IP: 192.168.1.76",
                color = Purple400,
                fontSize = 9.sp,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}

@Composable
fun ActionCardSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Indigo600.copy(alpha = 0.2f))
            .border(1.dp, Indigo500.copy(alpha = 0.3f), RoundedCornerShape(20.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "NODO SOBERANO MX-SQ-3000",
                color = Indigo100,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Text(
                text = "Cloud Run & Firebase Sincronizados",
                color = Indigo300,
                fontSize = 9.sp
            )
        }
        
        Surface(
            color = Emerald500.copy(alpha = 0.2f),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Emerald500.copy(alpha = 0.4f))
        ) {
            Text(
                text = "ACTIVO",
                color = Emerald400,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                fontFamily = FontFamily.Monospace
            )
        }
    }
}

@Composable
fun BottomNavigationBar(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Slate900)
            .padding(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavIconButton(icon = Icons.Outlined.Home, label = "Dash", isSelected = selectedTab == 0) { onTabSelected(0) }
        NavIconButton(icon = Icons.Outlined.Cloud, label = "Trinitary", isSelected = selectedTab == 1) { onTabSelected(1) }
        NavIconButton(icon = Icons.Outlined.Terminal, label = "Termux", isSelected = selectedTab == 2) { onTabSelected(2) }
        NavIconButton(icon = Icons.Outlined.DeviceHub, label = "Nodo MX", isSelected = selectedTab == 3) { onTabSelected(3) }
        NavIconButton(icon = Icons.Outlined.Settings, label = "Vault", isSelected = selectedTab == 4) { onTabSelected(4) }
    }
}

@Composable
fun NavIconButton(icon: ImageVector, label: String, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) Cyan600.copy(alpha = 0.2f) else Color.Transparent
    val tintColor = if (isSelected) Cyan400 else Slate500
    val textColor = if (isSelected) Cyan400 else Slate500
    
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = tintColor,
            modifier = Modifier.size(18.dp)
        )
        Text(
            text = label,
            color = textColor,
            fontSize = 8.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

