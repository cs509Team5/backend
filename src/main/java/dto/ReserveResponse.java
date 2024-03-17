package dto;

import com.example.arst5backend.controller.ReserveFlight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveResponse {
    String bookingDetails;
}
