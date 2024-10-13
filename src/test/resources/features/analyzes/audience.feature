Feature: Location functionality
  As a user of site invitro.ru
  I am able to see audience

  Background:
    Given the user navigated to the 'analyzes' page

  @audience @ui
  Scenario: User choose audience successfully
    When the user choose 'Врачам' audience
    Then noting

#  @audience
#  Scenario: user can't send wrong parameter
#    When the user choose 'Марсианам' audience
#    Then noting