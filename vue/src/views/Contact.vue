<template>
  <div id="main">
      <h2>Contact</h2>
      <form method="POST" action="https://example.com/api/reservation">
            <div id="email">
                <label for="e-mail">E-mail:</label>
                <input id="e-mail" name="e-mail" type="text" />
            </div>
            <div id="hasAccount">
                <label for="hasAccount">Do you already have an account with us?:</label>
                <input id="hasAccount" name="hasAccount" type="checkbox" />
            </div>
            <div id="question">
                <label for="question">What can we help you with?:</label>
                <input id="question" name="question" type="text-area" />
            </div>
            <div id="submit">
                <input type="submit" />
            </div>
        </form>
  </div>
</template>

<script>
export default {

}
</script>

<style>

div#main{
    margin-right: 75%;
    background-color: rgb(66, 64, 64);
    text-align: center;
    height: 50vh;
    border-radius: 25px;
    color: white;
    font-size: 20px;
}

h2{
    font-size: 50px;
}
div#email{
    margin-bottom: 20px;
}
div#hasAccount{
    margin-bottom: 20px;
}
div#question{
    margin-bottom: 20px;
}

</style>