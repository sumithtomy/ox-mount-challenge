package au.com.ox.mount.oxmountchallenge.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import au.com.ox.mount.oxmountchallenge.model.MachineModel;

class MachineAgeCheckerServiceTest {

  private MachineAgeCheckerService machineAgeCheckerService;

  @BeforeEach
  void setUp() throws Exception {
    machineAgeCheckerService = new MachineAgeCheckerService();
  }

  @AfterEach
  void tearDown() throws Exception {

    machineAgeCheckerService = null;
  }

  @DisplayName("Should Find the out of range values")
  @ParameterizedTest
  @MethodSource("provideSampleMachineData")
  void testFindOfRangesMachineAges(List<MachineModel> machineDataList) {
    // arrange
    List<String> expectedOutofRangeMachines = List.of("M1-1144", "M2-1144");
    // act
    assertDoesNotThrow(() -> {
      List<String> outOfRangeValuesActual = machineAgeCheckerService.getOutOfRangeValues(machineDataList);
      // assert
      outOfRangeValuesActual.forEach(System.out::println);
      assertNotNull(outOfRangeValuesActual);
      assertEquals(1, outOfRangeValuesActual.size());
      assertTrue(outOfRangeValuesActual.stream().allMatch(actual -> expectedOutofRangeMachines.contains(actual)));
    });

  }

  @Test
  void testShouldThrowErrorOnNullInput() {
    // arrage
    List<MachineModel> machineDataList = null;
    // act
    // assert
    assertThrows(NullPointerException.class, () -> {
      machineAgeCheckerService.getOutOfRangeValues(machineDataList);
    });

  }

  private static Stream<Arguments> provideSampleMachineData() {
    List<Arguments> argList = new ArrayList<Arguments>();
    List<MachineModel> machineDataList1 = new ArrayList<MachineModel>();
    List<MachineModel> machineDataList2 = new ArrayList<MachineModel>();

    // First Batch
    machineDataList1.add(new MachineModel("M1-1122", "1 month"));
    machineDataList1.add(new MachineModel("M1-2233", "6 month"));
    machineDataList1.add(new MachineModel("M1-2222", "10 years"));
    machineDataList1.add(new MachineModel("M1-1144", "90 years"));
    machineDataList1.add(new MachineModel("M1-1155", "15 Years"));
    machineDataList1.add(new MachineModel("M1-1155", "3 Years"));
    machineDataList1.add(new MachineModel("M1-1034", "10 months"));

    // Second Batch
    machineDataList2.add(new MachineModel("M2-1122", "1 month"));
    machineDataList2.add(new MachineModel("M2-2233", "6 month"));
    machineDataList2.add(new MachineModel("M2-2222", "10 years"));
    machineDataList2.add(new MachineModel("M2-1144", "90 years"));
    machineDataList2.add(new MachineModel("M2-1155", "7 Years"));
    machineDataList2.add(new MachineModel("M2-1155", "3 Years"));
    machineDataList2.add(new MachineModel("M2-1034", "20 months"));

    argList.add(Arguments.of(machineDataList1));
    argList.add(Arguments.of(machineDataList2));

    return argList.stream();
  }

}
