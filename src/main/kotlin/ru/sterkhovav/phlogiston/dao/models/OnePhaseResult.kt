package ru.sterkhovav.phlogiston.dao.models

import com.vladmihalcea.hibernate.type.json.JsonBinaryType
import com.vladmihalcea.hibernate.type.json.JsonStringType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import org.hibernate.annotations.TypeDefs
import ru.sterkhovav.phlogiston.dto.OnePhaseResultDto
import ru.sterkhovav.phlogiston.utils.OnePhaseParam
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "phlogiston_one_phase_result")
@TypeDefs(
    TypeDef(name = "json", typeClass = JsonStringType::class),
    TypeDef(name = "jsonb", typeClass = JsonBinaryType::class)
)
class OnePhaseResult(
    @field:Id
    @field:Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:Column(name = "author")
    val author: String,

    @field:Column(name = "create_date", nullable = false)
    var createDate: LocalDateTime = LocalDateTime.now(),

    @field:Type(type = "jsonb")
    @field:Column(name = "initial_params", nullable = false, columnDefinition = "jsonb")
    val initialParams: Map<OnePhaseParam, Double>,

    @field:Type(type = "jsonb")
    @field:Column(name = "count_result", nullable = false, columnDefinition = "jsonb")
    val countResult: Map<OnePhaseParam, Double>,
) {
    fun toDto() = OnePhaseResultDto(
        initialParams = this.initialParams,
        countResult = this.countResult,
    )
}



