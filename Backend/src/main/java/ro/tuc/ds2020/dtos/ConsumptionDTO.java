package ro.tuc.ds2020.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.tuc.ds2020.entities.Consumption;

import java.sql.Time;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumptionDTO {
    private Long id;
    private Date date;
    private Time hour;
    private String energyConsumption;
    private Long deviceId;

    public ConsumptionDTO convertEntityToDto(Consumption consumption)
    {
        return new ConsumptionDTO(
                consumption.getId(),
                consumption.getDate(),
                consumption.getHour(),
                consumption.getEnergyConsumption(),
                consumption.getDevice().getId()
        );
    }
}
