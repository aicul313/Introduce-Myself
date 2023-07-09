// Setting canvas

let canvas;
let ctx;

canvas = document.createElement("canvas");
ctx = canvas.getContext("2d");

canvas.width = 400;
canvas.height = 700;

document.body.appendChild(canvas);

let backgroundImage, rocketImage, enemyImage, bulletImage, gameoverImage;

let gameover = false;
let score = 0;

// rocket position
let rocketX = canvas.width/2-32;
let rocketY = canvas.height-64;

let bulletList = [];

function bullet(){
    this.x = 0;
    this.y = 0;
    this.init = function (){
        this.x = rocketX + 18;
        this.y = rocketY;
        this.alive = true;
        bulletList.push(this);
    };

    this.update = function (){
        this.y -= 7;
    };

    this.checkhit = function () {
        for(let i=0; i < enemyList.length; i++) {
            if (this.y <= enemyList[i].y && this.x >= enemyList[i].x && this.x <= enemyList[i].x+40){
                score++;
                this.alive = false;
                enemyList.splice(i, 1);
            }
        }
    }
}

function generateRandomValue(min, max){
    let randomNum = Math.floor(Math.random()*(max-min+1))+min;
    return randomNum;
}

let enemyList = [];

function enemy() {
    this.x = 0;
    this.y = 0;
    this.init = function (){
        this.x = generateRandomValue(0, canvas.width-48);
        this.y = 0;
        enemyList.push(this);
    };

    this.update = function () {
        this.y += 3;

        if(this.y >= canvas.height-48){
            gameover = true;
        }
    }
}

function loadImage(){
    backgroundImage = new Image();
    backgroundImage.src = "images/background.jpg";

    rocketImage = new Image();
    rocketImage.src = "images/rocket.png";

    enemyImage = new Image();
    enemyImage.src = "images/enemy.png";

    bulletImage = new Image();
    bulletImage.src = "images/bullet.png";

    gameoverImage = new Image();
    gameoverImage.src = "images/gameover.png";
}

let keysDown = {};

function setupkeyboardListener(){
    document.addEventListener("keydown", function(event){

        keysDown[event.keyCode] = true;
    });

    document.addEventListener("keyup", function(){
       delete keysDown[event.keyCode];

       if(event.keyCode == 32){
           createBullet(); // create bullet
       }
    });
}

function createBullet(){
    console.log("총알 생성");
    let b = new bullet();
    b.init();
    console.log("새로운 총알 리스트", bulletList);
}

function createEnemy(){
    const interval = setInterval(function () {
        let e = new enemy();
        e.init();
    }, 1000);
}

function update() {
    if(39 in keysDown) {
        rocketX += 5; // rocket's speed
    } // right

    if(37 in keysDown) {
        rocketX -= 5;
    } // left

    if(rocketX <= 0){
        rocketX = 0;
    }

    if(rocketX >= canvas.width - 64){
        rocketX = canvas.width - 64;
    }

    for(let i=0; i<bulletList.length; i++){
        if(bulletList[i].alive) {
            bulletList[i].update();
            bulletList[i].checkhit();
        }
    }

    for(let i=0; i<enemyList.length; i++){
        enemyList[i].update();
    }
}

function render(){
    ctx.drawImage(backgroundImage, 0, 0, canvas.width, canvas.height);
    ctx.drawImage(rocketImage, rocketX, rocketY);

    ctx.fillText(`Score : ${score}`, 20, 20);
    ctx.fillStyle = "white";
    ctx.font = "13px Arial";

    for(let i=0; i<bulletList.length; i++){
        if(bulletList[i].alive){
            ctx.drawImage(bulletImage, bulletList[i].x, bulletList[i].y);
        }
    }

    for(let i=0; i<enemyList.length; i++){
        ctx.drawImage(enemyImage, enemyList[i].x, enemyList[i].y);
    }
}

function main(){
    if(!gameover){
        update();
        render();
        requestAnimationFrame(main);
    } else {
        ctx.drawImage(gameoverImage, 10, 100, 380, 380);
    }

}

loadImage();
setupkeyboardListener();
createEnemy();
main();