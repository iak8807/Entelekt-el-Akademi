package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.model.ConceptRelation
import com.example.data.model.DisciplineId
import com.example.ui.theme.MatteGold
import com.example.ui.theme.ScholarGreen

@Composable
fun DisciplineBadge(
    discipline: DisciplineId,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = discipline.accentColor.copy(alpha = 0.14f),
        contentColor = discipline.accentColor,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, discipline.accentColor.copy(alpha = 0.3f))
    ) {
        Text(
            text = discipline.shortTitle,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun AcademicCard(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    borderColor: Color = MaterialTheme.colorScheme.outlineVariant,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    if (onClick != null) {
        Card(
            onClick = onClick,
            modifier = modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(containerColor = containerColor),
            border = BorderStroke(1.dp, borderColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                content = content
            )
        }
    } else {
        Card(
            modifier = modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(containerColor = containerColor),
            border = BorderStroke(1.dp, borderColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                content = content
            )
        }
    }
}

@Composable
fun SectionHeader(
    title: String,
    subtitle: String? = null,
    actionLabel: String? = null,
    onAction: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            if (!subtitle.isNullOrBlank()) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        if (actionLabel != null && onAction != null) {
            TextButton(onClick = onAction) {
                Text(actionLabel, style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

/**
 * Interactive Concept Map visual block combining custom Compose Canvas connector lines
 * with crisp Turkish concept relation cards.
 */
@Composable
fun ConceptMapDiagram(
    relations: List<ConceptRelation>,
    accentColor: Color,
    modifier: Modifier = Modifier
) {
    val lineColor = accentColor.copy(alpha = 0.5f)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f))
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        relations.forEach { rel ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, accentColor.copy(alpha = 0.4f))
                ) {
                    Text(
                        text = rel.source,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Column(
                    modifier = Modifier.width(104.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = rel.relation,
                        style = MaterialTheme.typography.labelSmall,
                        color = accentColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Canvas(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(14.dp)
                            .padding(horizontal = 6.dp)
                    ) {
                        val cy = size.height / 2f
                        drawLine(
                            color = lineColor,
                            start = Offset(0f, cy),
                            end = Offset(size.width - 6f, cy),
                            strokeWidth = 3f,
                            cap = StrokeCap.Round
                        )
                        val arrowPath = Path().apply {
                            moveTo(size.width, cy)
                            lineTo(size.width - 12f, cy - 5f)
                            lineTo(size.width - 12f, cy + 5f)
                            close()
                        }
                        drawPath(arrowPath, color = accentColor)
                    }
                }

                Surface(
                    modifier = Modifier.weight(1f),
                    color = accentColor.copy(alpha = 0.12f),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, accentColor.copy(alpha = 0.5f))
                ) {
                    Text(
                        text = rel.target,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

/**
 * Custom Canvas chart for 8-Discipline Comparison.
 */
@Composable
fun DisciplineComparisonChart(
    disciplinePcts: List<Pair<DisciplineId, Int>>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        disciplinePcts.forEach { (discipline, pct) ->
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = discipline.shortTitle,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "%$pct",
                        style = MaterialTheme.typography.labelSmall,
                        color = discipline.accentColor
                    )
                }
                val trackColor = MaterialTheme.colorScheme.surfaceVariant
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                ) {
                    drawRoundRect(
                        color = trackColor,
                        cornerRadius = CornerRadius(8f, 8f),
                        size = size
                    )
                    val filledWidth = (size.width * (pct.coerceIn(0, 100) / 100f))
                    if (filledWidth > 0f) {
                        drawRoundRect(
                            color = discipline.accentColor,
                            cornerRadius = CornerRadius(8f, 8f),
                            size = Size(filledWidth, size.height)
                        )
                    }
                }
            }
        }
    }
}

/**
 * Custom Canvas 12-Month Progression Bar Chart.
 */
@Composable
fun TwelveMonthProgressChart(
    monthlyPcts: List<Int>,
    modifier: Modifier = Modifier
) {
    val barColor = MaterialTheme.colorScheme.primary
    val trackColor = MaterialTheme.colorScheme.surfaceVariant
    val goldAccent = MatteGold

    Column(modifier = modifier.fillMaxWidth()) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        ) {
            val count = monthlyPcts.size.coerceAtLeast(1)
            val slotWidth = size.width / count
            val barWidth = (slotWidth * 0.56f).coerceAtLeast(8f)
            monthlyPcts.forEachIndexed { idx, pct ->
                val x = idx * slotWidth + (slotWidth - barWidth) / 2f
                drawRoundRect(
                    color = trackColor,
                    topLeft = Offset(x, 0f),
                    size = Size(barWidth, size.height),
                    cornerRadius = CornerRadius(6f, 6f)
                )
                val h = size.height * (pct.coerceIn(0, 100) / 100f)
                if (h > 0f) {
                    drawRoundRect(
                        color = if (pct >= 100) goldAccent else barColor,
                        topLeft = Offset(x, size.height - h),
                        size = Size(barWidth, h),
                        cornerRadius = CornerRadius(6f, 6f)
                    )
                }
            }
        }
        Spacer(Modifier.height(6.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            (1..12).forEach { m ->
                Text(
                    text = "A$m",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

/**
 * Custom Canvas Weekly Study Time & Continuity Sparkline/Bar Chart.
 */
@Composable
fun WeeklyStudyMinutesChart(
    dayLabelsAndMinutes: List<Pair<String, Long>>,
    targetMinutes: Long,
    modifier: Modifier = Modifier
) {
    val maxMinutes = maxOf(targetMinutes, dayLabelsAndMinutes.maxOfOrNull { it.second } ?: 30L, 30L)
    val activeColor = ScholarGreen
    val normalColor = MaterialTheme.colorScheme.primary
    val trackColor = MaterialTheme.colorScheme.surfaceVariant

    Column(modifier = modifier.fillMaxWidth()) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
        ) {
            val targetY = size.height - (size.height * (targetMinutes.toFloat() / maxMinutes.toFloat()))
            drawLine(
                color = MatteGold.copy(alpha = 0.7f),
                start = Offset(0f, targetY),
                end = Offset(size.width, targetY),
                strokeWidth = 2.5f
            )
            val count = dayLabelsAndMinutes.size.coerceAtLeast(1)
            val slot = size.width / count
            val bw = (slot * 0.48f).coerceAtLeast(10f)
            dayLabelsAndMinutes.forEachIndexed { index, (_, mins) ->
                val x = index * slot + (slot - bw) / 2f
                drawRoundRect(
                    color = trackColor,
                    topLeft = Offset(x, 0f),
                    size = Size(bw, size.height),
                    cornerRadius = CornerRadius(8f, 8f)
                )
                val h = size.height * (mins.toFloat() / maxMinutes.toFloat()).coerceIn(0f, 1f)
                if (h > 0f) {
                    drawRoundRect(
                        color = if (mins >= targetMinutes) activeColor else normalColor,
                        topLeft = Offset(x, size.height - h),
                        size = Size(bw, h),
                        cornerRadius = CornerRadius(8f, 8f)
                    )
                }
            }
        }
        Spacer(Modifier.height(6.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            dayLabelsAndMinutes.forEach { (label, mins) ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "${mins}dk",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

/**
 * Custom Canvas Line Chart for Exam Scores Trajectory.
 */
@Composable
fun ExamScoresLineChart(
    scores: List<Int>,
    modifier: Modifier = Modifier
) {
    val lineColor = MatteGold
    val dotColor = MaterialTheme.colorScheme.primary
    val gridColor = MaterialTheme.colorScheme.outlineVariant
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        // Horizontal reference lines (50%, 100%)
        drawLine(gridColor, Offset(0f, size.height / 2f), Offset(size.width, size.height / 2f), strokeWidth = 1.5f)
        drawLine(gridColor, Offset(0f, 0f), Offset(size.width, 0f), strokeWidth = 1.5f)
        if (scores.isEmpty()) return@Canvas

        val stepX = if (scores.size <= 1) size.width else size.width / (scores.size - 1)
        val path = Path()
        scores.forEachIndexed { i, score ->
            val x = if (scores.size == 1) size.width / 2f else i * stepX
            val y = size.height - (size.height * (score.coerceIn(0, 100) / 100f))
            if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
        }
        if (scores.size > 1) {
            drawPath(path, color = lineColor, style = Stroke(width = 5f, cap = StrokeCap.Round))
        }
        scores.forEachIndexed { i, score ->
            val x = if (scores.size == 1) size.width / 2f else i * stepX
            val y = size.height - (size.height * (score.coerceIn(0, 100) / 100f))
            drawCircle(color = dotColor, radius = 8f, center = Offset(x, y))
            drawCircle(color = lineColor, radius = 4f, center = Offset(x, y))
        }
    }
}

@Composable
fun CompetencyBarRow(
    label: String,
    score: Int,
    color: Color
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(label, style = MaterialTheme.typography.bodyMedium)
            Text("%$score", style = MaterialTheme.typography.labelLarge, color = color)
        }
        LinearProgressIndicator(
            progress = { (score.coerceIn(0, 100) / 100f) },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(CircleShape),
            color = color,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}
