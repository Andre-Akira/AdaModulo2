package org.ada.dto;


public record AirQuality(Integer id, String Station, String Benzene, String CO, String PM10, String NO2)
        implements Comparable<AirQuality> {

// Datetime,Station,Benzene,CO,PM10,PM2.5,NO2,O3,SO2,Toluene,TRS
    @Override
    public int compareTo(AirQuality o) {
        int comparacaoId = this.id.compareTo(o.id());
        if(comparacaoId != 0)
            return comparacaoId;

        return this.Station.compareTo(o.Station());
    }

    @Override
    public String toString() {
        return "AirQuality{" +
                "id=" + id +
                ", Station='" + Station + '\'' +
                ", Benzene='" + Benzene + '\'' +
                ", CO=" + CO +
                ", PM10=" + PM10 +
                ", NO2=" + NO2 +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        System.out.println("AirQuality::equals");

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AirQuality that = (AirQuality) o;

        if (!id.equals(that.id)) return false;
        return Station.equals(that.Station);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + Station.hashCode();
        return result;
    }
}



