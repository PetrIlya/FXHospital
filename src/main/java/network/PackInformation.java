package network;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackInformation {
    private String name;
    private int totalRecordsAmount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackInformation that = (PackInformation) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void incrementRecordsAmount() {
        this.totalRecordsAmount++;
    }

    public static int amountOfRecordsOfPack(String name, List<PackInformation> packInformationList) {
        return packInformationList.
                stream().
                dropWhile(pack -> !pack.getName().equals(name)).
                findFirst().
                map(PackInformation::getTotalRecordsAmount).get();
    }

    public final int getLastPageIndex(int recordsPerPage) {
        if (totalRecordsAmount % recordsPerPage != 0) {
            return (totalRecordsAmount / recordsPerPage) + 1;
        } else {
            return totalRecordsAmount / recordsPerPage;
        }
    }
}
