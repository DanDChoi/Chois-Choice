package choi.choice.framework.systems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserContextInfo {
    Integer birthYear;
   	UserGender gender;
   	ResidenceArea area;

   	public boolean isEmpty() {
   		return birthYear == null && gender == null;
   	}

}
