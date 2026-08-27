package com.example

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.*
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
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Web
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
val Slate200 = Color(0xFFE2E8F0)
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
                DashboardScreen()
            }
        }
    }
}

@Composable
fun DashboardScreen() {
    val logs = remember { mutableStateListOf<LogEntry>() }
    val coroutineScope = rememberCoroutineScope()
    
    LaunchedEffect(Unit) {
        logs.add(LogEntry(getCurrentTime(), "Welcome to Termux ALEPH-Σ", Cyan500))
        delay(500)
        logs.add(LogEntry(getCurrentTime(), "$ pkg search neurobin-sync", Slate500))
        delay(800)
        logs.add(LogEntry(getCurrentTime(), "[ OK ] Instance found at regional-qro-1", Emerald400))
        delay(600)
        logs.add(LogEntry(getCurrentTime(), "$ pkg install aleph-lattice-kem", Slate500))
        delay(1000)
        logs.add(LogEntry(getCurrentTime(), "Building reticular shards...", Slate300))
        delay(800)
        logs.add(LogEntry(getCurrentTime(), "[ √ { ♾️ } ~ ] SELLO DEFINITIVO / PERPETUO", Cyan400))
        delay(600)
        logs.add(LogEntry(getCurrentTime(), "STATUS: OPERATIVO_PLENO | NODO: SQ-3000_G6", Emerald400))
        delay(500)
        logs.add(LogEntry(getCurrentTime(), "Dashboard KUMI_AI.MX integrado con éxito.", Emerald500))
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Slate900,
        contentWindowInsets = WindowInsets.systemBars
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HeaderSection()
            
            TerminalSection(
                modifier = Modifier.weight(1f),
                logs = logs
            )
            
            BentoGridSection()
            
            ActionCardSection()
            
            BottomNavigationSection()
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
                    text = "Live Sync",
                    color = Emerald400,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            }
            Text(
                text = "v28.3.1-GOLD",
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
        // Telemetry Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Terminal Instance: ~ / node-mx-qro",
                color = Slate400,
                fontSize = 9.sp,
                fontFamily = FontFamily.Monospace,
                letterSpacing = 1.5.sp
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Export CSV Button
                Surface(
                    color = Slate800.copy(alpha = 0.6f),
                    shape = RoundedCornerShape(8.dp),
                    border = borderStroke(1.dp, Slate700),
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
                
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Slate700))
                    Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Slate700))
                }
            }
        }
        
        Divider(color = Slate800.copy(alpha = 0.5f), modifier = Modifier.padding(bottom = 12.dp))
        
        // Logs
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(logs) { log ->
                Text(
                    text = log.message,
                    color = log.color,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 11.sp,
                    fontStyle = if (log.color == Cyan500 || log.message.contains("Building")) FontStyle.Italic else FontStyle.Normal,
                    fontWeight = if (log.color == Cyan500) FontWeight.Bold else FontWeight.Normal
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
        
        // Footer Sello
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
        
        val csvHeader = "Timestamp,Message\n"
        var csvData = csvHeader
        
        logs.forEach { log ->
            // Escape quotes and wrap in quotes for CSV safety
            val escapedMessage = log.message.replace("\"", "\"\"")
            csvData += "${log.timestamp},\"$escapedMessage\"\n"
        }
        
        file.writeText(csvData)
        Toast.makeText(context, "Logs exported to CSV: ${file.absolutePath}", Toast.LENGTH_LONG).show()
    } catch (e: Exception) {
        Toast.makeText(context, "Error exporting CSV: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}

fun getCurrentTime(): String {
    val sdf = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())
    return sdf.format(Date())
}

fun borderStroke(width: androidx.compose.ui.unit.Dp, color: Color) = androidx.compose.foundation.BorderStroke(width, color)

@Composable
fun BentoGridSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(176.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Metric 1: Security
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(24.dp))
                .background(Slate800.copy(alpha = 0.6f))
                .border(1.dp, Slate700.copy(alpha = 0.5f), RoundedCornerShape(24.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Cyan500.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = "Security",
                        tint = Cyan400,
                        modifier = Modifier.size(16.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "SECURITY LEVEL",
                    color = Slate500,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "ML-KEM-1024",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "NIST CAT-5",
                color = Cyan400,
                fontSize = 9.sp,
                fontFamily = FontFamily.Monospace
            )
        }

        // Metric 2: Latency
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(24.dp))
                .background(Slate800.copy(alpha = 0.6f))
                .border(1.dp, Slate700.copy(alpha = 0.5f), RoundedCornerShape(24.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Purple500.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Speed,
                        contentDescription = "Latency",
                        tint = Purple400,
                        modifier = Modifier.size(16.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "NODE LATENCY",
                    color = Slate500,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "25.0ms ±0.4",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            }
            Text(
                text = "QRO_MX_S1",
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
            .clip(RoundedCornerShape(24.dp))
            .background(Indigo600.copy(alpha = 0.2f))
            .border(1.dp, Indigo500.copy(alpha = 0.3f), RoundedCornerShape(24.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "POST-QUANTUM VAULT",
                color = Indigo100,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                letterSpacing = 1.sp
            )
            Text(
                text = "FIPS 203 Cryptographic Standards",
                color = Indigo300,
                fontSize = 10.sp
            )
        }
        
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = Indigo500, contentColor = Color.White),
            shape = RoundedCornerShape(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = "AUTHENTICATE",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }
    }
}

@Composable
fun BottomNavigationSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .background(Slate900.copy(alpha = 0.8f))
            .border(1.dp, Slate800, CircleShape)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavIconButton(icon = Icons.Outlined.Home, isSelected = true)
        NavIconButton(icon = Icons.Outlined.Web, isSelected = false)
        NavIconButton(icon = Icons.Outlined.Settings, isSelected = false)
        NavIconButton(icon = Icons.Outlined.Person, isSelected = false)
    }
}

@Composable
fun NavIconButton(icon: ImageVector, isSelected: Boolean) {
    val backgroundColor = if (isSelected) Cyan600 else Color.Transparent
    val tintColor = if (isSelected) Color.White else Slate500
    
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable { },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tintColor,
            modifier = Modifier.size(24.dp)
        )
    }
}

