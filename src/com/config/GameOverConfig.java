package com.config;

public class GameOverConfig {
	public static int chooseOption = -1;
	
	public static void restartConfig(){
		ChessStateConfig.chessWinType = 0;
		ChessStateConfig.chessGoType = 0;
		ChessStateConfig.isWhiteDown = true;
		ChessStateConfig.isGameStart = false;
//		ChessStateConfig.isGoBack = false;
		for(int i=0; i<ChessStateConfig.downChessType.length; i++){
			for(int j=0; j<ChessStateConfig.downChessType[i].length; j++)
			{
				ChessStateConfig.downChessType[i][j] = 0;
			}
		}
		ChessStateConfig.isGoBack = false;
		GameOverConfig.chooseOption = -1;
		GoBackConfig.chooseOption = -1;
		GoBackConfig.countBlackBack = 1;
		GoBackConfig.countWhiteBack = 1;
	}
}
