package io.github.jatar.teamMG;

import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.action.DialogAction;
import io.papermc.paper.registry.data.dialog.body.DialogBody;
import io.papermc.paper.registry.data.dialog.input.DialogInput;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickCallback;
import net.kyori.adventure.translation.GlobalTranslator;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@SuppressWarnings({"UnstableApiUsage"})
public class TeamDialogs {
    private static @NonNull Component renderToString(String key, Locale userLocale) {
        return GlobalTranslator.render(Component.translatable(key), userLocale);
    }

    public static @NonNull Dialog createTeamDialog(Component wrongText, Locale userLocale) {
        Component teamCommandNameLabel = renderToString("gui.menu.createTeam.commandNameLabel", userLocale);
        Component teamNameLabel = renderToString("gui.menu.createTeam.nameLabel", userLocale);

        return Dialog.create(builder -> builder.empty()
                .base(DialogBase.builder(renderToString("gui.menu.createTeam.title", userLocale))
                        .body(
                                List.of(
                                        DialogBody.plainMessage(wrongText)
                                )
                        )
                        .inputs(List.of(
                                DialogInput.text("teamCommandName", teamCommandNameLabel)
                                        .width(300)
                                        .maxLength(30)
                                        .build(),
                                DialogInput.text("teamName", teamNameLabel)
                                        .width(300)
                                        .maxLength(30)
                                        .build()
                        ))
                        .build()
                ).type(DialogType.confirmation(
                        ActionButton.create(
                                renderToString("gui.menu.buttons.cancel", userLocale),
                                null,
                                100,
                                null // If we set the action to null, it doesn't do anything and closes the dialogue
                        ),
                        ActionButton.create(
                                renderToString("gui.menu.buttons.save", userLocale),
                                null,
                                100,
                                DialogAction.customClick(TeamDialogCallbacks::acceptNewTeamDialog, ClickCallback.Options.builder()
                                        .uses(1) // Set the number of uses for this callback. Defaults to 1
                                        .lifetime(ClickCallback.DEFAULT_LIFETIME) // Set the lifetime of the callback. Defaults to 12 hours
                                        .build())
                        )
                ))
        );
    }

    public static @NonNull Dialog removeTeamCheck(Locale userLocale) {
        return  Dialog.create(builder -> builder.empty()
                .base(DialogBase.builder(renderToString("gui.menu.removeTeamCheck.title", userLocale))
                        .build()
                ).type(DialogType.confirmation(
                        ActionButton.create(
                                renderToString("gui.menu.removeTeamCheck.cancel", userLocale),
                                null,
                                120,
                                null // If we set the action to null, it doesn't do anything and closes the dialogue
                        ),
                        ActionButton.create(
                                renderToString("gui.menu.removeTeamCheck.save", userLocale),
                                null,
                                120,
                                DialogAction.customClick(TeamDialogCallbacks::acceptRemoveTeamDialog, ClickCallback.Options.builder()
                                        .uses(1)
                                        .lifetime(ClickCallback.DEFAULT_LIFETIME)
                                        .build())
                        )
                ))
        );
    }

    public static @NonNull Dialog listJoinRequests(Locale userLocale) {
        List<DialogInput> inputs = new ArrayList<>();
        // TODO: add request list
        // TODO: First add settings to set team as private

        // all players will be listed with boolean selector
        return  Dialog.create(builder -> builder.empty()
                .base(DialogBase.builder(renderToString("gui.menu.listJoinRequest.title", userLocale))
                        .inputs(inputs)
                        .build()
                ).type(DialogType.confirmation(
                        ActionButton.create(
                                renderToString("gui.menu.buttons.cancel", userLocale),
                                null,
                                100,
                                null
                        ),
                        ActionButton.create(
                                renderToString("gui.menu.listJoinRequest.save", userLocale),
                                null,
                                100,
                                DialogAction.customClick(TeamDialogCallbacks::acceptSelectedRequests, ClickCallback.Options.builder()
                                        .uses(1)
                                        .lifetime(ClickCallback.DEFAULT_LIFETIME)
                                        .build())
                        )
                ))
        );
    }
}
