package com.tasktimetracker;

import com.tasktimetracker.dto.main.TimeRecordWithDurationDTO;
import com.tasktimetracker.entity.TimeRecord;
import com.tasktimetracker.exception.TimeRecordException;
import com.tasktimetracker.mapper.TimeRecordMapper;
import com.tasktimetracker.service.impl.TimeRecordServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TimeRecordServiceImplTest {

    @Mock
    private TimeRecordMapper timeRecordMapper;

    @InjectMocks
    private TimeRecordServiceImpl timeRecordService;

    @Test
    @DisplayName("Успешное получение записей времени за период")
    void getEmployeeTimeRecordsForPeriod_Success() {
        UUID employeeId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime from = now.minusDays(2);
        LocalDateTime to = now.plusDays(1);

        TimeRecord record = TimeRecord.builder()
                .id(UUID.randomUUID())
                .employeeId(employeeId)
                .taskId(UUID.randomUUID())
                .startTime(now.minusHours(2))
                .endTime(now)
                .description("Work")
                .build();

        when(timeRecordMapper.findByEmployeeIdAndPeriod(employeeId, from, to))
                .thenReturn(List.of(record));

        List<TimeRecordWithDurationDTO> result = timeRecordService.getEmployeeTimeRecordsForPeriod(employeeId, from, to);

        assertFalse(result.isEmpty());
        assertEquals(120L, result.get(0).durationMinutes());
    }

    @Test
    @DisplayName("Ошибка: дата 'от' больше даты 'до'")
    void getEmployeeTimeRecordsForPeriod_ThrowsIllegalArgument_WhenFromIsAfterTo() {
        UUID employeeId = UUID.randomUUID();
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.now().minusDays(2);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> timeRecordService.getEmployeeTimeRecordsForPeriod(employeeId, from, to));

        assertEquals("from должно быть меньше to", exception.getMessage());
        verify(timeRecordMapper, never()).findByEmployeeIdAndPeriod(any(), any(), any());
    }

    @Test
    @DisplayName("Ошибка: нет данных за период")
    void getEmployeeTimeRecordsForPeriod_ThrowsException_WhenNoData() {
        UUID employeeId = UUID.randomUUID();
        LocalDateTime from = LocalDateTime.now().minusDays(2);
        LocalDateTime to = LocalDateTime.now();

        when(timeRecordMapper.findByEmployeeIdAndPeriod(employeeId, from, to))
                .thenReturn(Collections.emptyList());

        assertThrows(TimeRecordException.class,
                () -> timeRecordService.getEmployeeTimeRecordsForPeriod(employeeId, from, to));
    }
}