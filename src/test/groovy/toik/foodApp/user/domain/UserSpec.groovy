package toik.foodApp.user.domain

import spock.lang.Specification
import toik.foodApp.security.requests.RegistrationRequest
import toik.foodApp.user.dto.AlreadyTakenException
import toik.foodApp.user.dto.UserDto

class UserSpec extends Specification {

  private UserFacade userFacade = new UserConfiguration().userFacade()

  def "should create client and add him to the database" () {
    when: "client is registered"
      UserDto client = userFacade.registerUser(new RegistrationRequest("Jane", "Doe", "jane123", "janePswd",
                                               "jane@gmail.com", "123456789"))
    then: "database contains this client"
      userFacade.findByUsername(client.username)
  }

  def "should return nothing when user is not in system" () {
    when: "system is asked for a user that is not present"
      Optional<UserDto> result = userFacade.findByUsername("username not in system")
    then: "system give nothing"
      result == Optional.empty()
  }

  def "should return all users with given ids" () {
    given: "there are some users in system"
      UserDto clientJane = userFacade.registerUser(new RegistrationRequest("Jane", "Doe", "jane123",
        "janePswd", "jane@gmail.com", "123456789"))

      UserDto clientMike = userFacade.registerUser(new RegistrationRequest("Mike", "Doe", "mike123",
        "mikePswd", "mike@gmail.com", "123789456"))
    when: "system is asked for users with given ids"
      List<UserDto> users = userFacade.getUsersById([clientJane.getUserId(), clientMike.getUserId()])
    then: "system returns all users with given ids"
      users.size() == 2
  }

  def "should return user with given id" () {
    given: "there is a user in system"
      UserDto clientJane = userFacade.registerUser(new RegistrationRequest("Jane", "Doe", "jane123",
          "janePswd", "jane@gmail.com", "123456789"))
    when: "system is asked for user with given id"
      Optional<UserDto> userDto = userFacade.findByUserId(clientJane.getUserId())
    then: "system returns user with given id"
      userDto.get().userId == clientJane.userId
      userDto.get().name == clientJane.name
      userDto.get().password == clientJane.password
      userDto.get().username == clientJane.username
      userDto.get().phoneNumber == clientJane.phoneNumber
      userDto.get().email == clientJane.email
  }

  def "should return empty array when users are not in system" () {
    when: "system is asked for users with given ids"
      List<UserDto> users = userFacade.getUsersById([null, null])
    then: "system returns empty array"
      users.size() == 0
  }

  def "should return one user when there is given one correct id and one not" () {
    given: "there are some users in system"
      UserDto clientJane = userFacade.registerUser(new RegistrationRequest("Jane", "Doe", "jane123",
          "janePswd", "jane@gmail.com", "123456789"))

      UserDto clientMike = userFacade.registerUser(new RegistrationRequest("Mike", "Doe", "mike123",
          "mikePswd", "mike@gmail.com", "123789456"))
    when: "system is asked for users with given ids"
      List<UserDto> users = userFacade.getUsersById([clientJane.getUserId(), null])
    then: "system return one user with given correct id"
      users.size() == 1
  }

  def "should thrown exception when there are two users with the same username" () {
    given: "there is a user in system"
      UserDto clientJaneDoe = userFacade.registerUser(new RegistrationRequest("Jane", "Doe",
          "jane123", "janePswd", "jane@gmail.com", "123456789"))
    when: "some other user tries to register with username that is already taken"
      UserDto clientJaneSmith = userFacade.registerUser(new RegistrationRequest("Jane", "Smith",
          "jane123", "janePswd", "jane@gmail.com", "123456789"))
    then: "system thrown exception"
      thrown(AlreadyTakenException)
  }

  def "should return all users" () {
    given: "there are some users in system"
      UserDto clientJaneDoe = userFacade.registerUser(new RegistrationRequest("Jane", "Doe",
          "jane123", "janePswd", "jane@gmail.com", "123456789"))
      UserDto clientJaneSmith = userFacade.registerUser(new RegistrationRequest("Jane", "Smith",
          "janeSmith123", "janePswd", "jane@gmail.com", "123789456"))
    UserDto clientMike = userFacade.registerUser(new RegistrationRequest("Mike", "Doe", "mike123",
        "mikePswd", "mike@gmail.com", "987654321"))
    when: "system is asked for all users"
      List<UserDto> users = userFacade.findAllUsers()
    then: "system return three users"
      users.size() == 3
  }
}
