package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .doesNotContain("Unknown")
                .startsWith("S")
                .startsWithIgnoringCase("s")
                .isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 5);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .doesNotContain("Unknown")
                .startsWith("T")
                .startsWithIgnoringCase("t")
                .isEqualTo("Tetrahedron");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 5);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .doesNotContain("Unknown")
                .startsWith("C")
                .startsWithIgnoringCase("c")
                .isEqualTo("Cube");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(1, 5);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .doesNotContain("Sphere", "Tetrahedron", "Cube")
                .startsWith("U")
                .isEqualTo("Unknown object");
    }

    @Test
    void whenGetNumberOfVerticesIsFour() {
        Box box = new Box(4, 5);
        int numberOfVertices = box.getNumberOfVertices();
        assertThat(numberOfVertices)
                .isEven()
                .isNotNegative()
                .isNotZero()
                .isGreaterThan(0)
                .isEqualTo(4);
    }

    @Test
    void whenGetNumberOfVerticesIsMinusOne() {
        Box box = new Box(1, 5);
        int numberOfVertices = box.getNumberOfVertices();
        assertThat(numberOfVertices)
                .isOdd()
                .isNegative()
                .isNotZero()
                .isLessThan(0)
                .isEqualTo(-1);
    }

    @Test
    void whenVertexZeroIsExistTrue() {
        Box box = new Box(0, 5);
        boolean isExist = box.isExist();
        assertThat(isExist)
                .isNotEqualTo(false)
                .isTrue();
    }
    @Test
    void whenVertexFourIsExistTrue() {
        Box box = new Box(4, 5);
        boolean isExist = box.isExist();
        assertThat(isExist).isTrue();
    }

    @Test
    void whenVertexOneIsExistFalse() {
        Box box = new Box(1, 5);
        boolean isExist = box.isExist();
        assertThat(isExist).isFalse();
    }

    @Test
    void whenVertexFourEdgeFiveThenGetArea() {
        Box box = new Box(4, 5);
        double area = box.getArea();
        assertThat(area)
                .isGreaterThan(43)
                .isLessThan(44)
                .isPositive()
                .isEqualTo(43.3, withPrecision(0.1));
    }

    @Test
    void whenVertexZeroEdgeFiveThenGetArea() {
        Box box = new Box(0, 5);
        double area = box.getArea();
        assertThat(area)
                .isGreaterThan(314)
                .isLessThan(315)
                .isPositive()
                .isEqualTo(314.15, withPrecision(0.1));
    }

    @Test
    void whenVertexOneEdgeFiveThenZero() {
        Box box = new Box(1, 5);
        double area = box.getArea();
        assertThat(area)
                .isLessThan(1)
                .isGreaterThan(-1)
                .isZero();
    }
}