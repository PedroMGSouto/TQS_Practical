a)
For EmployeeRepositoryTest we have:
> assertThat( found ).isEqualTo(alex);
> assertThat(fromDb).isNull();
> assertThat(fromDb).isNotNull();
> assertThat(fromDb.getEmail()).isEqualTo( emp.getEmail());
> assertThat(fromDb).isNull();
> assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(),
bob.getName());

For EmployeeRestControllerIT we have:
> assertThat(found).extracting(Employee::getName).containsOnly("bob");

For EmployeeRestControllerTemplateIT we have:
> assertThat(found).extracting(Employee::getName).containsOnly("bob");
> assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
> assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex");

For EmployeeService_UnitTest we have:
> assertThat(found.getName()).isEqualTo(name);
> assertThat(fromDb).isNull();
> assertThat(doesEmployeeExist).isEqualTo(true);
> assertThat(doesEmployeeExist).isEqualTo(false);
> assertThat(fromDb.getName()).isEqualTo("john");
> assertThat(fromDb).isNull();
> assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(),
bob.getName());

b)
> In 'EmployeeService_UnitTest' we can observe tests made with a mocked repository. Therefore we can check if all of the EmployeeService methods are working correctly while keeping the behavior of the repository predictable (because of Mockito). So we don't need the implementation of the repository or the database for doing tests to the rest of the program.

c)

Libraries:
> @Mock: Mockito Library;
> @MockBean: Spring Boot Library;
  ---------------------------------
Use:
> @Mock: Usually the best for testing;
> @MockBean: Good for testing that involves dependencies from a SpringBoot container and mocking of the container beans;
  ---------------------------------
(Note: @MockBean replaces any existing beans of the same type in the application context. However if there are no beans
of the same type a new one is created)

d)
> Used to define the application properties that the tests will abide. For example if we need to test a database, the
related connection configurations will be defined there, and every call that needs this data will look for it there.