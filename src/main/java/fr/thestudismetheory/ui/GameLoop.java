package fr.thestudismetheory.ui;

public abstract class GameLoop {

		private boolean running = false;
		
		private double delta;
		
		public void run(double time) {	
			
			delta = time;
			running = true;
			startup();
			
			double nextTime = (double)System.nanoTime();
			
			while(running) {
				double currTime = (double)System.nanoTime();
				
				if(currTime >= nextTime) {
					nextTime += delta;
					update();
					if(currTime < nextTime) draw();
				} else {
					int sleepTime = (int)(1000.0*(nextTime - currTime));
					
					if(sleepTime > 0) {
						try {
							Thread.sleep(sleepTime);
						} catch(InterruptedException e) {}
					}
				}
				shutdown();
			}
			
		}
		
		public void stop() {
			running = false;
		}
		
		public abstract void startup();
		
		public abstract void shutdown();
		
		public abstract void update();

		public abstract void draw();
		
}
