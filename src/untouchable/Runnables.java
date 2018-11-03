package com.kalgames.untouchable;

public class Runnables {

    private final Untouchable screen;

    private Runnable changeToMenu, setZIndexTransition, changeToLevel_1, changeToLevel_2, changeToLevel_3,
    changeToLevel_4, changeToLevel_5, changeToLevel_6, changeToLevel_7, changeToLevel_8, changeToLevel_9,
    changeToLevel_10, changeToChallengeScreen, changeToChallengeOverScreen, changeToLevels,
    stageRemove, addTransition, changeToGamesScreen;

    public Runnables(final Untouchable instance){
        screen = instance;

        changeToMenu = new Runnable() {
            @Override
            public void run() {
                screen.setScreen(screen.menuScreen);
            }
        };

        setZIndexTransition = new Runnable() {
            @Override
            public void run() {
                screen.stage.getActors().peek().setZIndex(0);
            }
        };

        changeToLevel_1 = new Runnable() {
            @Override
            public void run() {
                screen.setScreen(screen.level_1);
            }
        };

        changeToLevel_2 = new Runnable() {
            @Override
            public void run() {
                screen.setScreen(screen.level_2);
            }
        };

        changeToLevel_3 = new Runnable() {
            @Override
            public void run() {
                screen.setScreen(screen.level_3);
            }
        };

        changeToLevel_4 = new Runnable() {
            @Override
            public void run() {
                screen.setScreen(screen.level_4);
            }
        };

        changeToLevel_5 = new Runnable() {
            @Override
            public void run() {
                screen.setScreen(screen.level_5);
            }
        };

        changeToLevel_6 = new Runnable() {
            @Override
            public void run() {
                screen.setScreen(screen.level_6);
            }
        };

        changeToLevel_7 = new Runnable() {
            @Override
            public void run() {
                screen.setScreen(screen.level_7);
            }
        };

        changeToLevel_8 = new Runnable() {
            @Override
            public void run() {
                screen.setScreen(screen.level_8);
            }
        };

        changeToLevel_9 = new Runnable() {
            @Override
            public void run() {
                screen.setScreen(screen.level_9);
            }
        };

        changeToLevel_10 = new Runnable() {
            @Override
            public void run() {
                screen.setScreen(screen.level_10);
            }
        };

        changeToChallengeScreen = new Runnable() {
            @Override
            public void run() {
                screen.setScreen(screen.challengeScreen);
            }
        };

        changeToChallengeOverScreen = new Runnable() {
            @Override
            public void run() {
                screen.setScreen(screen.challengeOverScreen);
            }
        };

        changeToLevels = new Runnable() {
            @Override
            public void run() {
                screen.setScreen(screen.levels);
            }
        };

        stageRemove = new Runnable() {
            @Override
            public void run() {
                screen.stage.getActors().peek().remove();
            }
        };

        addTransition = new Runnable() {
            @Override
            public void run() {
                screen.stage.addActor(screen.transition);
            }
        };

        changeToGamesScreen = new Runnable(){
            @Override
            public void run() {
                screen.setScreen(screen.gamesScreen);
            }
        };
    }

    public Runnable changeToMenu(){
        return changeToMenu;
    }

    public Runnable setZIndexTransition(){
        return setZIndexTransition;
    }

    public Runnable changeToLevel_1(){
        return changeToLevel_1;
    }

    public Runnable changeToLevel_2(){
        return changeToLevel_2;
    }

    public Runnable changeToLevel_3(){
        return changeToLevel_3;
    }

    public Runnable changeToLevel_4(){
        return changeToLevel_4;
    }

    public Runnable changeToLevel_5(){
        return changeToLevel_5;
    }

    public Runnable changeToLevel_6(){
        return changeToLevel_6;
    }

    public Runnable changeToLevel_7(){
        return changeToLevel_7;
    }

    public Runnable changeToLevel_8(){
        return changeToLevel_8;
    }

    public Runnable changeToLevel_9(){
        return changeToLevel_9;
    }

    public Runnable changeToLevel_10(){
        return changeToLevel_10;
    }

    public Runnable changeToChallengeScreen(){
        return changeToChallengeScreen;
    }

    public Runnable changeToChallengeOverScreen(){
        return changeToChallengeOverScreen;
    }

    public Runnable changeToLevels(){
        return changeToLevels;
    }

    public Runnable stageRemove(){
        return stageRemove;
    }

    public Runnable addTransition(){
        return addTransition;
    }

    public Runnable changeToGamesScreen(){
        return changeToGamesScreen;
    }
}
