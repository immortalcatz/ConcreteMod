function love.load()
	types = {gate = "fence_gate", wall = "wall_gate"}
	states = {"open", "closed"}
	colors = {"white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "silver", "cyan", "purple", "blue", "brown", "green", "red", "black"}
	
	for _, color in pairs(colors) do
		for _, state in pairs(states) do
			for gateTypeName, gateType in pairs(types) do
				love.filesystem.write(color .. "-" .. gateTypeName .. "-" .. state .. ".json", "{\n\t\"parent\": \"block/" .. gateType .. "_" .. state .. "\",\n\t\"textures\": {\n\t\t\"texture\": \"blocks/concrete_" .. color .. "\"\n\t}\n}")
			end
		end
	end
	
	love.system.openURL("file://"..love.filesystem.getSaveDirectory())
	love.event.quit()
end