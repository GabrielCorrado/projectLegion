# projectLegion
A swarm research tool

Lightly summarized copy of Kirsch's original document, with addition in [] by Nick Corrado:
An environment for exploring emergence, specifically the relationship between the different levels within a swarm.
Has multiple levels of increasing complexity, with adjustable parameters at each level. Changes at one level affect behaviors at lower levels. One goal of the project is to look at the results that occur by making changes at once level, and trying to enact similar results by making different changes at a different level. In short, will allow for the investigation of the relationship between the levels in an emergent system.

There are 4 levels. Lowest is a collection of cellular automata that produces a pseudo-random process that, left alone, will either never stabilize or will take so long to do so that it can be thought of as ongoing. This will be realized as an iterative process of cells taking on a particular status from a palette of possible states. Determining next state and number of choices [i.e. just black and white or black, white, red, green, blue, yellow, etc] are user definable parameters. [Currently we are generating from a palette of just black and white, and this is the board on the left.]

Next level up is a purely information representation. Instead of looking at the raw data from the cellular automata, this level collects the disparate, wildly fluctuating information and synthesizes a representation that shows a distillation of the changes below in a manner the human can reasonable process. [This is the "polarity" level. This is the board on the right.]

The third level consists of a traditional swarm of mobile agents traversing the informational representation of the second level. These agents have sensors for gathering information about the environment in which they exists, and for communicating with other agents at this level. They also have the capabilty of altering their environment [i.e. flipping the color of the cell they are sitting on]. [Currently these agents move around at random on the board when you click, or when you click and drag, with a 30% chance of flipping their cell's color from black to white or vice versa.]

The fourth level is a human interface through which the humans can influence the swarm agents at the level below. The hope is to provide many different types of interaction, some significant in scope, others subtle. [These aren't programmed at all yet.] These types of influence include real-time feedback to the agents based on what the humans observe in levels 2 and 3. We [i.e., Kirsch and Palmer] want to explore different approaches to human assisted swarms: taking over serial single agents, giving high-level general directives, changing global information, etc.
